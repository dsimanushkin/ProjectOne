package com.devlab74.projectone.ui.main.state

import com.devlab74.projectone.model.BlogPostRequest
import com.devlab74.projectone.model.UserRequest

data class MainViewState(

    var blogPostRequest: BlogPostRequest? = null,
    var userRequest: UserRequest? = null

)