package com.gorkem.githubrepo.data.api

import com.gorkem.githubrepo.data.model.GithubRepoResponse
import retrofit2.Retrofit

open class GithubServiceImpl constructor(
    retrofit: Retrofit
) : GithubService {

    private var service: GithubService = retrofit.create(GithubService::class.java)

    override suspend fun getRepoList(user: String): List<GithubRepoResponse> {
        return service.getRepoList(user)
    }


}