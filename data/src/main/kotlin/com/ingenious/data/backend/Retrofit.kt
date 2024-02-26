package com.ingenious.data.backend

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.Request
import okio.Buffer
import retrofit2.Response

internal fun <T> Response<T>.toException(): Exception {
    return IllegalStateException(collectLogData())
}

private fun <T> Response<T>.collectLogData(): String {
    val raw = this.raw()

    return StringBuilder()
        .append(raw.request.url)
        .append("\nrequest: ")
        .append(raw.request.toBodyText())
        .append("\nresponse: ")
        .append(code())
        .append("\n")
        .append(errorBody()?.string() ?: body()?.toString())
        .toString()
}

private fun Request.toBodyText(): String {
    val copy = newBuilder().build()
    val buffer = Buffer()
    copy.body?.toString()
    return buffer.readUtf8()
}

internal suspend fun <T> wrapRequest(
    dispatcher: CoroutineDispatcher,
    request: suspend () -> Response<T>
): Result<T> {
    return withContext(dispatcher) {
        try {
            val response = request()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.success(body)
            } else {
                Result.failure(response.toException())
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}