package com.devlab74.projectone.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class BlogPost(

    @Expose
    @SerializedName("_id")
    var _id: String? = null,

    @Expose
    @SerializedName("blog_image")
    var blog_image: String? = null,

    @Expose
    @SerializedName("title")
    var title: String? = null,

    @Expose
    @SerializedName("body")
    var body: String? = null,

    @Expose
    @SerializedName("posted_by")
    var posted_by: String? = null,

    @Expose
    @SerializedName("date_posted")
    var date_posted: Date? = null,

    @Expose
    @SerializedName("reposts_count")
    var reposts_count: Int? = null,

    @Expose
    @SerializedName("likes_count")
    var likes_count: Int? = null

) {
    override fun equals(other: Any?): Boolean {

        if (javaClass != other?.javaClass) {
            return false
        }

        other as BlogPost

        if (_id != other._id) {
            return false
        }

        return true
    }

    override fun toString(): String {
        return "BlogPost(blog_image=$blog_image, title=$title, body=$body, posted_by=$posted_by, date_posted=$date_posted, reposts_count=$reposts_count, likes_count=$likes_count)"
    }
}