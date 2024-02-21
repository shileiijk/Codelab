package com.shilei.ijk.okretro

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.io.Serializable

@JvmInline
value class RequestResult<out T>(
    val value: Any?
) : Serializable {
    public val isSuccess: Boolean get() = value !is Failure && value != null

    public val isFailure: Boolean get() = value is Failure

    public val isEmpty: Boolean get() = value == null

    public fun getOrNull(): T? =
        when {
            isFailure -> null
            else -> value as T
        }

    public fun exceptionOrNull(): Throwable? =
        when (value) {
            is Failure -> value.exception
            else -> null
        }

    public override fun toString(): String =
        when (value) {
            is Failure -> value.toString() // "Failure($exception)"
            else -> "Success($value)"
        }

    public companion object {
        @JvmName("success")
        public fun <T> success(value: T): RequestResult<T> = RequestResult(value)

        @JvmName("failure")
        public fun <T> failure(exception: Throwable): RequestResult<T> = RequestResult(createFailure(exception))

        @JvmName("empty")
        public fun <T> empty(): RequestResult<T> = RequestResult(null)
    }

    internal class Failure(
        @JvmField
        val exception: Throwable
    ) : Serializable {
        override fun equals(other: Any?): Boolean = other is Failure && exception == other.exception
        override fun hashCode(): Int = exception.hashCode()
        override fun toString(): String = "Failure($exception)"
    }
}

fun createFailure(exception: Throwable): Any = RequestResult.Failure(exception)

fun RequestResult<*>.throwOnFailure() {
    if (value is RequestResult.Failure) throw value.exception
}

public inline fun <R> runCatching(block: () -> R): RequestResult<R> {
    return try {
        RequestResult.success(block())
    } catch (e: Throwable) {
        RequestResult.failure(e)
    }
}

public inline fun <T, R> T.runCatching(block: T.() -> R): RequestResult<R> {
    return try {
        RequestResult.success(block())
    } catch (e: Throwable) {
        RequestResult.failure(e)
    }
}

public fun <T> RequestResult<T>.getOrThrow(): T {
    throwOnFailure()
    return value as T
}

public inline fun <R, T : R> RequestResult<T>.getOrElse(onFailure: (exception: Throwable) -> R): R {
    return when (val exception = exceptionOrNull()) {
        null -> value as T
        else -> onFailure(exception)
    }
}

public fun <R, T : R> RequestResult<T>.getOrDefault(defaultValue: R): R {
    if (isFailure) return defaultValue
    return value as T
}

public inline fun <R, T> RequestResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R
): R {
    return when (val exception = exceptionOrNull()) {
        null -> onSuccess(value as T)
        else -> onFailure(exception)
    }
}

public inline fun <R, T> RequestResult<T>.map(transform: (value: T) -> R): RequestResult<R> {
    return when {
        isSuccess -> RequestResult.success(transform(value as T))
        else -> RequestResult(value)
    }
}

public inline fun <R, T> RequestResult<T>.mapCatching(transform: (value: T) -> R): RequestResult<R> {
    return when {
        isSuccess -> runCatching { transform(value as T) }
        else -> RequestResult(value)
    }
}

public inline fun <R, T : R> RequestResult<T>.recover(transform: (exception: Throwable) -> R): RequestResult<R> {
    return when (val exception = exceptionOrNull()) {
        null -> this
        else -> RequestResult.success(transform(exception))
    }
}

public inline fun <R, T : R> RequestResult<T>.recoverCatching(transform: (exception: Throwable) -> R): RequestResult<R> {
    return when (val exception = exceptionOrNull()) {
        null -> this
        else -> runCatching { transform(exception) }
    }
}

public suspend inline fun <T> RequestResult<T>.onFailure(crossinline action: (exception: Throwable) -> Unit): RequestResult<T> {
    exceptionOrNull()?.let {
        withContext(Dispatchers.Main) {
            action(it)
        }
    }
    return this
}

public suspend inline fun <T> RequestResult<T>.onSuccess(crossinline action: (value: T) -> Unit): RequestResult<T> {
    if (isSuccess) {
        withContext(Dispatchers.Main) {
            action(value as T)
        }
    }
    return this
}

public suspend inline fun <T> RequestResult<T>.onEmpty(crossinline action: () -> Unit): RequestResult<T> {
    if (this.isEmpty) {
        withContext(Dispatchers.Main) {
            action()
        }
    }
    return this
}

suspend inline fun <T> execute(crossinline block: suspend () -> Response<T>): RequestResult<T> {
    return try {
        val response = block()
        if (response.isSuccessful) {
            val body = response.body()
            if (body == null || (body is List<*> && (body as List<*>).isEmpty())) {
                Timber.w("execute: empty, code: ${response.code()}, msg: ${response.message()}")
                RequestResult.empty()
            } else {
                RequestResult.success(body)
            }
        } else {
            Timber.e("execute: failure, code: ${response.code()}, msg: ${response.message()}")
            RequestResult.failure(ApiException(response.code(), response.message()))
        }
    } catch (exception: Exception) {
        Timber.e("execute: happened an exception, message: ${exception.message}")
        RequestResult.failure(exception)
    }
}

suspend inline fun <T> execute2(crossinline block: suspend () -> Call<T>): RequestResult<T> {
    return execute { block().execute() }
}
