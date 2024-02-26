package com.ingenious.data.backend

import com.ingenious.data.user.UsersApi
import data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

class BackendApiProviderImpl @Inject constructor(
    private val baseUrl: String,
    private val apiVersion: String
) : BackendApiProvider {

    private val retrofit by lazy {
        val clientBuilder = OkHttpClient()
            .newBuilder()
            .addInterceptor(HeadersInterceptor(apiVersion))

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            clientBuilder.addInterceptor(loggingInterceptor)
        }

        val client = clientBuilder.build()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(ConverterFactory())
            .build()
    }

    private val usersApi by lazy {
        retrofit.create(UsersApi::class.java)
    }

    override fun provideUsersApi(): UsersApi = usersApi
}