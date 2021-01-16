package com.devlab74.projectone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.devlab74.projectone.util.*
import com.devlab74.projectone.util.Constants.Companion.TESTING_NETWORK_DELAY
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class NetworkBoundResource<ResponseObject, ViewStateType> {

    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)

        GlobalScope.launch(IO) {
            delay(TESTING_NETWORK_DELAY) // Just for testing purposes

            withContext(Main) {
                val apiResponse = createCall()

                result.addSource(apiResponse) {response ->
                    result.removeSource(apiResponse)

                    handleNetworkCall(response)
                }
            }
        }
    }

    private fun handleNetworkCall(response: GenericApiResponse<ResponseObject>) {
        when(response) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse -> {
                Timber.d("DEBUG: NetworkBoundResource: ${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                Timber.d("DEBUG: NetworkBoundResource: HTTP 204. Returned nothing!")
                onReturnError("HTTP 204. Returned nothing!")
            }
        }
    }

    private fun onReturnError(message: String) {
        result.value = DataState.error(message)
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData() = result as LiveData<DataState<ViewStateType>>
}