package com.gorkem.githubrepo.di.module

import android.app.Application
import com.google.gson.Gson
import com.gorkem.githubrepo.BuildConfig
import com.gorkem.githubrepo.data.api.GithubService
import com.gorkem.githubrepo.data.api.GithubServiceImpl
import com.gorkem.githubrepo.data.local.GithubRepoDB
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDb(app: Application) = GithubRepoDB.getAppDataBase(app)

    @Provides
    @Singleton
    fun provideFavouriteDao(db: GithubRepoDB) = db.favouriteDao()

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): GithubService =
        GithubServiceImpl(retrofit)
}