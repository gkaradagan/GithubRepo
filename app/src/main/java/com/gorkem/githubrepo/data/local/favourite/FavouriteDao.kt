package com.gorkem.githubrepo.data.local.favourite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourite")
    suspend fun getFavourites(): List<Favourite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favourite: Favourite)

    @Delete
    suspend fun delete(favourite: Favourite)
}