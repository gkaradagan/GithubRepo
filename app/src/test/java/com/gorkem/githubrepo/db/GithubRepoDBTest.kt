package com.gorkem.githubrepo.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gorkem.githubrepo.data.local.GithubRepoDB
import com.gorkem.githubrepo.data.local.favourite.Favourite
import com.gorkem.githubrepo.util.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class GithubRepoDBTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var githubRepoDB: GithubRepoDB


    @Before
    fun initDb() {
        githubRepoDB = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            GithubRepoDB::class.java
        ).build()
    }

    @After
    fun closeDb() {
        githubRepoDB.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `insert favourite item`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        runBlocking {
            val favourite = Favourite(1)
            githubRepoDB.favouriteDao().insert(favourite)

            val favouriteList = githubRepoDB.favouriteDao().getFavourites()
            assert(favouriteList.isNotEmpty())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `delete favourite item`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        runBlocking {
            val favourite = Favourite(1)
            githubRepoDB.favouriteDao().insert(favourite)

            githubRepoDB.favouriteDao().delete(favourite)
            val favouriteList = githubRepoDB.favouriteDao().getFavourites()
            assert(favouriteList.isEmpty())
        }
    }

}