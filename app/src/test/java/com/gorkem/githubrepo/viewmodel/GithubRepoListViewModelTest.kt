package com.gorkem.githubrepo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gorkem.githubrepo.data.api.GithubService
import com.gorkem.githubrepo.data.local.favourite.FavouriteDao
import com.gorkem.githubrepo.data.model.GithubRepoResponse
import com.gorkem.githubrepo.data.model.Owner
import com.gorkem.githubrepo.data.model.ServiceResult
import com.gorkem.githubrepo.data.repository.GithupRepository
import com.gorkem.githubrepo.ui.list.GithubRepoListViewModel
import com.gorkem.githubrepo.util.CoroutinesTestRule
import com.gorkem.githubrepo.util.LiveDataTestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
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
class GithubRepoListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val favouriteDao = Mockito.mock(FavouriteDao::class.java)
    private lateinit var repository: GithupRepository
    private val service = Mockito.mock(GithubService::class.java)
    private lateinit var viewModel: GithubRepoListViewModel

    private lateinit var response: GithubRepoResponse

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = GithupRepository(service, favouriteDao)
        viewModel = GithubRepoListViewModel(repository)

        val owner = Owner("", 1, "")
        response = GithubRepoResponse(false, 1, "gorkem", 1, owner, 1, 1)
    }
    @ExperimentalCoroutinesApi
    @Test
    fun `repository return data and check viewModel get Success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            runBlocking {
                Mockito.`when`(
                    service.getRepoList(
                        anyString()
                    )
                ).thenReturn(listOf(response, response, response))

                val request = viewModel.getRepoList(
                    anyString()
                )

                Assert.assertNotNull(request)
                Assert.assertNotNull(LiveDataTestUtil.getValue(viewModel.repoList))
                Assert.assertThat(
                    LiveDataTestUtil.getValue(viewModel.repoList).status,
                    CoreMatchers.`is`(ServiceResult.Status.SUCCESS)
                )
            }
        }
    @ExperimentalCoroutinesApi
    @Test
    fun `repository return null and check viewModel get Error`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            runBlocking {
                Mockito.`when`(
                    service.getRepoList(anyString())
                ).thenReturn(null)

                val request = viewModel.getRepoList(anyString())

                Assert.assertNotNull(request)
                val value = LiveDataTestUtil.getValue(viewModel.repoList)
                Assert.assertNotNull(value)
                Assert.assertThat(
                    LiveDataTestUtil.getValue(viewModel.repoList).status,
                    CoreMatchers.`is`(ServiceResult.Status.ERROR)
                )
            }
        }

}