package com.devlab74.projectone.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRequest(

    @Json(name = "status")
    var status: String? = null,

    @Json(name = "message")
    var message: String? = null,

    @Json(name = "data")
    var data: User? = null

)