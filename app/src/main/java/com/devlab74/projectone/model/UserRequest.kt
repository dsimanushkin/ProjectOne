package com.devlab74.projectone.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRequest(

    @Expose
    @SerializedName("status")
    var status: String? = null,

    @Expose
    @SerializedName("message")
    var message: String? = null,

    @Expose
    @SerializedName("data")
    var data: User? = null

) {

    override fun toString(): String {
        return "UserRequest(status=$status, message=$message, data=$data)"
    }
}