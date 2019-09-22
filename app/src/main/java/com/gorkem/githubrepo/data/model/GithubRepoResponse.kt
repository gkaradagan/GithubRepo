package com.gorkem.githubrepo.data.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepoResponse(
    var favourite: Boolean = false,
    val id: Int,
    val name: String,
    val open_issues_count: Int,
    val owner: Owner,
    val stargazers_count: Int,
    val watchers_count: Int
) : Response()