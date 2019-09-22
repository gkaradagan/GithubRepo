package com.gorkem.githubrepo.data.api

import com.gorkem.githubrepo.data.model.GithubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    companion object {
        const val ENDPOINT = "https://api.github.com/users/"
    }

    @GET("{user}/repos")
    suspend fun getRepoList(
        @Path("user") user: String
    ): List<GithubRepoResponse>
}