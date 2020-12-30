package com.devlab74.projectone.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class User(

    @Expose
    @SerializedName("profile_image")
    var profile_image: String? = null,

    @Expose
    @SerializedName("full_name")
    var full_name: String? = null,

    @Expose
    @SerializedName("username")
    var username: String? = null,

    @Expose
    @SerializedName("email")
    var email: String? = null,

    @Expose
    @SerializedName("dob")
    var dob: Date? = null,

    @Expose
    @SerializedName("date_created")
    var date_created: Date? = null

) {

    override fun toString(): String {
        return "User(profile_image=$profile_image, full_name=$full_name, username=$username, email=$email, dob=$dob, date_created=$date_created)"
    }
}