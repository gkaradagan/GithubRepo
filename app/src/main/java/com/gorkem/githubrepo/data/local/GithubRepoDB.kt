package com.gorkem.githubrepo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gorkem.githubrepo.data.local.favourite.Favourite
import com.gorkem.githubrepo.data.local.favourite.FavouriteDao


@Database(entities = [Favourite::class], version = 1)
abstract class GithubRepoDB : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao

    companion object {
        fun getAppDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GithubRepoDB::class.java,
            "GithubRepoDB"
        ).build()
    }

}