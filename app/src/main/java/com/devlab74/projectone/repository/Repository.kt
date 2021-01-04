package com.devlab74.projectone.repository

import androidx.lifecycle.LiveData
import com.devlab74.projectone.api.RetrofitBuilder
import com.devlab74.projectone.model.BlogPostRequest
import com.devlab74.projectone.model.UserRequest
import com.devlab74.projectone.ui.main.state.MainViewState
import com.devlab74.projectone.util.ApiSuccessResponse
import com.devlab74.projectone.util.Constants.Companion.API_ACCESS_TOKEN
import com.devlab74.projectone.util.DataState
import com.devlab74.projectone.util.GenericApiResponse

object Repository {
    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<BlogPostRequest, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<BlogPostRequest>) {
                result.value = DataState.data(
                    message = null,
                    data = MainViewState(
                        blogPostRequest = response.body,
                        userRequest = null
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<BlogPostRequest>> {
                val headers: Map<String, String> = mapOf("api-access-token" to API_ACCESS_TOKEN)
                return RetrofitBuilder.apiService.getBlogPosts(headers)
            }
        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<UserRequest, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<UserRequest>) {
                result.value = DataState.data(
                    message = null,
                    data = MainViewState(
                        blogPostRequest = null,
                        userRequest = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<UserRequest>> {
                val headers: Map<String, String> = mapOf("api-access-token" to API_ACCESS_TOKEN)
                return RetrofitBuilder.apiService.getUser(userId, headers)
            }
        }.asLiveData()
    }
}