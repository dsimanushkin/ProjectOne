package com.devlab74.projectone.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class User(

    @Json(name = "profile_image")
    var profileImage: String? = null,

    @Json(name = "full_name")
    var fullName: String? = null,

    @Json(name = "username")
    var username: String? = null,

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "dob")
    var dob: Date? = null,

    @Json(name = "date_created")
    var dateCreated: Date? = null

)