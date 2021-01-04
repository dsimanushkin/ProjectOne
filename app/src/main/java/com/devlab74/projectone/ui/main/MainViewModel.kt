package com.devlab74.projectone.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.devlab74.projectone.model.BlogPostRequest
import com.devlab74.projectone.model.UserRequest
import com.devlab74.projectone.repository.Repository
import com.devlab74.projectone.ui.main.state.MainStateEvent
import com.devlab74.projectone.ui.main.state.MainViewState
import com.devlab74.projectone.util.AbsentLiveData
import com.devlab74.projectone.util.DataState

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent) {stateEvent ->
            stateEvent?.let {
                handleStateEvent(it)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        when(stateEvent) {
            is MainStateEvent.GetBlogPostsEvent -> {
                return Repository.getBlogPosts()
            }
            is MainStateEvent.GetUserEvent -> {
                return Repository.getUser(stateEvent.userId)
            }
            is MainStateEvent.None -> {
                return AbsentLiveData.create()
            }
        }
    }

    fun setBlogPostData(blogPostRequest: BlogPostRequest) {
        val update = getCurrentStateOrNew()
        update.blogPostRequest = blogPostRequest
        _viewState.value = update
    }

    fun setUser(userRequest: UserRequest) {
        val update = getCurrentStateOrNew()
        update.userRequest = userRequest
        _viewState.value = update
    }

    private fun getCurrentStateOrNew(): MainViewState {
        val value = viewState.value?.let {
            it
        }?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }
}