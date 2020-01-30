package com.gorkem.githubrepo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gorkem.githubrepo.di.ViewModelFactory
import com.gorkem.githubrepo.di.ViewModelKey
import com.gorkem.githubrepo.ui.detail.GithubRepoDetailViewModel
import com.gorkem.githubrepo.ui.list.GithubRepoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepoListViewModel::class)
    abstract fun bindGithubRepoListViewModel(viewModel: GithubRepoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepoDetailViewModel::class)
    abstract fun bindGithubRepoDetailViewModel(viewModel: GithubRepoDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
