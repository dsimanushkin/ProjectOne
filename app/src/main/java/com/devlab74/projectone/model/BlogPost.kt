package com.devlab74.projectone.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class BlogPost(

    @Json(name = "_id")
    var userId: String? = null,

    @Json(name = "blog_image")
    var blogImage: String? = null,

    @Json(name = "title")
    var title: String? = null,

    @Json(name = "body")
    var body: String? = null,

    @Json(name = "posted_by")
    var postedBy: String? = null,

    @Json(name = "date_posted")
    var datePosted: Date? = null,

    @Json(name = "reposts_count")
    var repostsCount: Int? = null,

    @Json(name = "likes_count")
    var likesCount: Int? = null

) {
    override fun equals(other: Any?): Boolean {

        if (javaClass != other?.javaClass) {
            return false
        }

        other as BlogPost

        if (userId != other.userId) {
            return false
        }

        return true
    }
}