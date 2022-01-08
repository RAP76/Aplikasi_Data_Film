package com.mws.aplikasidatafilm.api

import android.content.Context
import com.mws.aplikasidatafilm.`interface`.APIInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    const val BASE_URL = "http://10.113.64.130/film/public/"

    fun create(context: Context): APIInterface {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIInterface::class.java)
    }

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}