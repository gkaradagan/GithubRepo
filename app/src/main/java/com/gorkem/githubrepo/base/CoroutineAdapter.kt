/*
package com.gorkem.githubrepo.base

import okhttp3.Request
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

sealed class ServiceResult<out T> {
    data class Success<T>(val data: T?) : ServiceResult<T>()
    data class Failure(val statusCode: Int?) : ServiceResult<Nothing>()
    data class Exception(val exception: java.lang.Exception?) : ServiceResult<Nothing>()
    object NetworkError : ServiceResult<Nothing>()
}

abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    override final fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    override final fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
}

class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, ServiceResult<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<ServiceResult<T>>) = proxy.enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val code = response.code()
            val result = if (code in 200 until 300) {
                val body = response.body()
                ServiceResult.Success(body)
            } else {
                ServiceResult.Failure(code)
            }

            callback.onResponse(this@ResultCall, Response.success(result))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val result = if (t is IOException) {
                ServiceResult.NetworkError
            } else {
                ServiceResult.Failure(null)
            }

            callback.onResponse(this@ResultCall, Response.success(result))
        }
    })

    override fun cloneImpl() = ResultCall(proxy.clone())
}

class ResultAdapter<T>(
    private val clazz: Class<T>
): CallAdapter<T, Call<ServiceResult<T>>> {
    override fun responseType() = clazz
    override fun adapt(call: Call<T>): Call<ServiceResult<T>> = ResultCall(call)
}

class CoroutineAdapter : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                Result::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    ResultAdapter(getRawType(resultType))
                }
                else -> null
            }
        }
        else -> null
    }
}*/
