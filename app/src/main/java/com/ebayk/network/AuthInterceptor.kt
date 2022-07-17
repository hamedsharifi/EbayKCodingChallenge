package com.ebayk.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", Credentials.basic(getUsername(), getPassword())).build()
        return chain.proceed(authenticatedRequest)
    }

    private external fun getUsername(): String
    private external fun getPassword(): String
}