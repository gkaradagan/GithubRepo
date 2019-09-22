package com.gorkem.githubrepo.data.model

data class ServiceResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ServiceResult<T> {
            return ServiceResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String? = null, data: T? = null): ServiceResult<T> {
            return ServiceResult(Status.ERROR, data, message)
        }

        fun <T> loading(): ServiceResult<T> {
            return ServiceResult(Status.LOADING, null, null)
        }
    }
}