package com.gorkem.githubrepo.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gorkem.githubrepo.data.api.GithubService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GithubServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: GithubService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `check url path`() {
        runBlocking {
            enqueueResponse("repolist.json")
            val resultResponse = service.getRepoList("JakeWharton")

            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request.path, CoreMatchers.`is`("/JakeWharton/repos"))
        }
    }

    @Test
    fun `get data succes`() {
        runBlocking {
            enqueueResponse("repolist.json")
            val resultResponse = service.getRepoList("JakeWharton")
            Assert.assertNotNull(resultResponse)
        }
    }

    @Test
    fun `check one item is correct`() {
        runBlocking {
            enqueueResponse("repolist.json")
            val resultResponse = service.getRepoList("JakeWharton")

            val repo = resultResponse[0]
            Assert.assertThat(repo.name, CoreMatchers.`is`("abs.io"))
            Assert.assertThat(repo.owner.login, CoreMatchers.`is`("JakeWharton"))
            Assert.assertThat(repo.id, CoreMatchers.`is`(3070104))
        }
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse.setBody(
                source.readString(Charsets.UTF_8)
            )
        )
    }
}