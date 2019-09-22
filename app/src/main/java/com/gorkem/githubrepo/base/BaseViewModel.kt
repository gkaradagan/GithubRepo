package com.gorkem.githubrepo.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.data.model.ServiceResult
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel(), LifecycleObserver {
    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    protected val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    protected fun <T> call(
        liveData: MutableLiveData<ServiceResult<T>>,
        networkCall: suspend () -> ServiceResult<T>
    ) {
        uiScope.launch {
            liveData.value = ServiceResult.loading()
            val task = withContext(Dispatchers.Main) {
                // background thread
                networkCall.invoke()
            }
            liveData.value = task
        }
    }

    protected fun <T> callDBAndReturn(
        liveData: MutableLiveData<T>,
        dbCall: suspend () -> T) {
        uiScope.launch {
            val task = withContext(Dispatchers.Main) {
                // background thread
                dbCall.invoke()
            }
            liveData.value = task
        }
    }

    protected fun doBackground(dbCall: suspend () -> Unit) {
        uiScope.launch {
            withContext(Dispatchers.Main) {
                // background thread
                dbCall.invoke()
            }
        }
    }
}