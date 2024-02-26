package com.ingeniuos.common

fun <T> Result<T>.requirePayload(): T {
    return requireNotNull(getOrNull()) { "payload is null for $this" }
}

fun <T> Result<T?>.toException(): Throwable {
    return exceptionOrNull() ?: IllegalArgumentException("$this")
}