package com.devlab74.projectone.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogPostRequest(

    @Expose
    @SerializedName("status")
    var status: String? = null,

    @Expose
    @SerializedName("message")
    var message: String? = null,

    @Expose
    @SerializedName("blog_posts_total")
    var blog_posts_total: Int? = null,

    @Expose
    @SerializedName("data")
    var data: List<BlogPost>? = null

) {

    override fun toString(): String {
        return "BlogPostRequest(status=$status, message=$message, blog_posts_total=$blog_posts_total, data=$data)"
    }

}