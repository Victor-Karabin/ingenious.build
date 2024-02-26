package com.ingenious.data.backend

import com.google.gson.GsonBuilder
import kotlinx.datetime.LocalDateTime
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

internal class ConverterFactory : Converter.Factory() {

    private val gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, ISO8601Deserializer())
            .create()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {

        return GsonConverterFactory.create(gson)
            .responseBodyConverter(type, annotations, retrofit)
    }
}