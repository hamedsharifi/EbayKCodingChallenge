package com.ebayk.network


import com.ebayk.core.model.Ad

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Services {

    @GET("/coding-challenge/api/v1/ads/{ad_id}")
    suspend fun getAd(@Path("ad_id") adId: String?): Ad

    companion object {
        private var retrofitService: Services? = null
        private var okHttpClient: OkHttpClient =
            OkHttpClient().newBuilder().addNetworkInterceptor(AuthInterceptor())
                .build()

        fun getInstance(): Services {
            return retrofitService ?: Retrofit.Builder()
                .baseUrl("https://gateway.ebay-kleinanzeigen.de/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Services::class.java)
        }
    }

}