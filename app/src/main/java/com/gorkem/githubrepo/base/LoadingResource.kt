package com.gorkem.githubrepo.base

interface LoadingResource<T>{
    fun onError(message: String?)
    fun onShowLoading()
    fun onHideLoading(isSucces: Boolean)
    fun onSuccess(data: T?)
}