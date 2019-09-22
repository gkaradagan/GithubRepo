package com.gorkem.githubrepo.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.gorkem.githubrepo.R
import com.gorkem.githubrepo.base.ui.LoadingDialog
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.data.model.ServiceResult
import com.gorkem.githubrepo.di.Injectable
import com.gorkem.githubrepo.util.ConnectivityUtil
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<DB : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(),
    Injectable, HasSupportFragmentInjector {

    private var loadingDialog: LoadingDialog? = null
    private var simpleDialog: AlertDialog? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    lateinit var binding: DB

    @get:LayoutRes
    abstract val layoutRes: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val vm: V

    private val savable = Bundle()

    protected fun <T> instanceState() = InstanceStateProvider.Nullable<T>(savable)
    protected fun <T> instanceState(defaultValue: T) =
        InstanceStateProvider.NotNull(savable, defaultValue)


    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            savable.putAll(savedInstanceState.getBundle("_state"))
        }
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        lifecycle.addObserver(vm)
    }

    override fun onDestroy() {
        lifecycle.removeObserver(vm)
        onHideLoading()
        hideSimpleDialog()
        super.onDestroy()
    }

    fun onShowLoading() {
        if (loadingDialog == null)
            loadingDialog = LoadingDialog(this)
        loadingDialog!!.show()
    }

    fun onHideLoading() {
        if (loadingDialog != null && loadingDialog!!.isShowing)
            loadingDialog!!.dismiss()
        loadingDialog = null
    }

    private fun hideSimpleDialog() {
        if (simpleDialog != null && simpleDialog!!.isShowing)
            simpleDialog!!.dismiss()
    }

    fun onError(message: String) {
        simpleDialog.whenNull {
            simpleDialog = MaterialAlertDialogBuilder(this@BaseActivity)
                .setMessage(message)
                .setPositiveButton(R.string.general_ok) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    simpleDialog = null;
                }
                .setCancelable(true)
                .create()
            simpleDialog!!.setCanceledOnTouchOutside(true)
        }
        simpleDialog!!.show()
    }

    fun listen(
        livedata: MutableLiveData<ServiceResult<List<GithubRepoResponse>>>,
        listen: SlientLoadingResourceImpl<List<GithubRepoResponse>>
    ) {
        if (!ConnectivityUtil.isInternetAvailable(this)) {
            onError(getString(R.string.not_connected_to_internet))
        } else {
            livedata.observe(this) { listen.onChanged(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle("_state", savable)
        super.onSaveInstanceState(outState)
    }
}