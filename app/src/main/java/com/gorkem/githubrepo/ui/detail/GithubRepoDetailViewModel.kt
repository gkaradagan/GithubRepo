package com.gorkem.githubrepo.ui.detail

import com.gorkem.githubrepo.base.BaseViewModel
import com.gorkem.githubrepo.data.local.favourite.Favourite
import com.gorkem.githubrepo.data.repository.GithupRepository
import javax.inject.Inject

class GithubRepoDetailViewModel @Inject constructor(var repository: GithupRepository) :
    BaseViewModel() {

    fun insertFavourite(favourite: Favourite) =
        doBackground { repository.insertFavourite(favourite) }

    fun deleteFavourite(favourite: Favourite) =
        doBackground { repository.deleteFavourite(favourite) }

}