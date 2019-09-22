package com.gorkem.githubrepo.di

import android.app.Application
import com.gorkem.githubrepo.GithubApplication
import com.gorkem.githubrepo.di.module.ActivityModule
import com.gorkem.githubrepo.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: GithubApplication)
}
