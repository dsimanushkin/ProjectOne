package com.devlab74.projectone.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlogPostRequest(

    @Json(name = "status")
    var status: String? = null,

    @Json(name = "message")
    var message: String? = null,

    @Json(name = "blog_posts_total")
    var blogPostsTotal: Int? = null,

    @Json(name = "data")
    var data: List<BlogPost>? = null

)