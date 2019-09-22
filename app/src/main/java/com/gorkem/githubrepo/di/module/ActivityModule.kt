package com.gorkem.githubrepo.di.module


import com.gorkem.githubrepo.ui.detail.GithubRepoDetailActivity
import com.gorkem.githubrepo.ui.list.GithubRepoListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeGithubRepoListActivity(): GithubRepoListActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeGithubRepoDetailActivity(): GithubRepoDetailActivity
}
