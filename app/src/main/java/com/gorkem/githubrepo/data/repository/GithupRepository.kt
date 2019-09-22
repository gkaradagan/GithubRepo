package com.gorkem.githubrepo.data.repository

import com.gorkem.githubrepo.base.BaseRepository
import com.gorkem.githubrepo.base.whenNonNull
import com.gorkem.githubrepo.data.api.GithubService
import com.gorkem.githubrepo.data.local.favourite.Favourite
import com.gorkem.githubrepo.data.local.favourite.FavouriteDao
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.data.model.ServiceResult
import javax.inject.Inject

class GithupRepository @Inject constructor(
    private var service: GithubService,
    private var favouriteDao: FavouriteDao
) :
    BaseRepository() {

    suspend fun getRepoList(
        user: String
    ): ServiceResult<List<GithubRepoResponse>> {
        val result = call { service.getRepoList(user) }
        if (result.status == ServiceResult.Status.SUCCESS && result.data != null) {
            val favourites = getFavourites()
            favourites.whenNonNull {
                favourites.forEach {
                    result.data.find { data -> data.id == it.id }?.favourite = true
                }
            }
        }
        return result
    }

    suspend fun getFavourites(): List<Favourite> {
        return favouriteDao.getFavourites()
    }

    suspend fun insertFavourite(favourite: Favourite) {
        favouriteDao.insert(favourite)
    }

    suspend fun deleteFavourite(favourite: Favourite) {
        favouriteDao.delete(favourite)
    }
}