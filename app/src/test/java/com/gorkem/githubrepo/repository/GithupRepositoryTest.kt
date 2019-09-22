package com.gorkem.githubrepo.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gorkem.githubrepo.data.api.GithubService
import com.gorkem.githubrepo.data.local.favourite.FavouriteDao
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.data.model.Owner
import com.gorkem.githubrepo.data.model.ServiceResult
import com.gorkem.githubrepo.data.repository.GithupRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito


@RunWith(JUnit4::class)
class GithupRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: GithupRepository

    private val service = Mockito.mock(GithubService::class.java)

    private val favouriteDao = Mockito.mock(FavouriteDao::class.java)

    private lateinit var response: GithubRepoResponse

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = GithupRepository(service, favouriteDao)
        val owner = Owner("", 1, "")
        response = GithubRepoResponse(false, 1, "gorkem", 1, owner, 1, 1)

    }

    @Test
    fun `repository return null and Service Result will be success`() {
        runBlocking {
            Mockito.`when`(
                service.getRepoList(
                    anyString()
                )
            ).thenReturn(listOf(response, response, response))

            val request = repository.getRepoList(
                anyString()
            )
            Assert.assertNotNull(request)
            Assert.assertNotNull(request.data)
        }
    }

    @Test
    fun `repository return null and Service Result will be error`() {

        runBlocking {
            Mockito.`when`(
                service.getRepoList(
                    anyString()
                )
            ).thenReturn(null)

            val request = repository.getRepoList(
                anyString()
            )

            Assert.assertNotNull(request)//ServiceResult
            Assert.assertNull(request.data)
            Assert.assertThat(request.status, CoreMatchers.`is`(ServiceResult.Status.ERROR))

        }
    }
}