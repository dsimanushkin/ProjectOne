package com.devlab74.projectone.api

import com.devlab74.projectone.util.Constants.Companion.BASE_URL
import com.devlab74.projectone.util.LiveDataCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

object RetrofitBuilder {

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build()))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
    }

    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

}