package com.devlab74.projectone.api

import androidx.lifecycle.LiveData
import com.devlab74.projectone.model.BlogPostRequest
import com.devlab74.projectone.model.UserRequest
import com.devlab74.projectone.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("user/get/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<UserRequest>>

    @GET("blog/getall")
    fun getBlogPosts(): LiveData<GenericApiResponse<BlogPostRequest>>

}