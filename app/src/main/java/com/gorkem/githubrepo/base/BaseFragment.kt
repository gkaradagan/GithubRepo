package com.gorkem.githubrepo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gorkem.githubrepo.di.Injectable

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment(), Injectable {

    lateinit var viewDataBinding: DB

    abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return viewDataBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLifecycle().addObserver(viewModel)
    }

    fun getActivityContext()
            : BaseActivity<*, *> {
        return activity as BaseActivity<*, *>
    }

    override fun onDetach() {
        getLifecycle().removeObserver(viewModel)
        super.onDetach()
    }
}
