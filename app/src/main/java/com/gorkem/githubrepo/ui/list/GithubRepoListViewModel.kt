package com.gorkem.githubrepo.ui.list

import androidx.lifecycle.MutableLiveData
import com.gorkem.githubrepo.base.BaseViewModel
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.data.model.ServiceResult
import com.gorkem.githubrepo.data.repository.GithupRepository
import javax.inject.Inject

class GithubRepoListViewModel @Inject constructor(var repository: GithupRepository) :
    BaseViewModel() {

    val repoList = MutableLiveData<ServiceResult<List<GithubRepoResponse>>>()

    fun getRepoList(user: String) = call(repoList) { repository.getRepoList(user) }

}