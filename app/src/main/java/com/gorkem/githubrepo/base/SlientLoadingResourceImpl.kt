package com.gorkem.githubrepo.base

abstract class SlientLoadingResourceImpl<T>(context: BaseActivity<*, *>) :
    LoadingResourceImpl<T>(context) {

    override fun onShowLoading() {

    }

    override fun onHideLoading(isSucces: Boolean) {

    }
}