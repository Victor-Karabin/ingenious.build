package com.ingenious.data.backend

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor(
    private val apiVersion: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", apiVersion)
            .build()

        return chain.proceed(modifiedRequest)
    }
}