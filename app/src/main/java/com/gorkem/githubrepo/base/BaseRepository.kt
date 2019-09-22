package com.gorkem.githubrepo.base

import com.google.gson.Gson
import com.gorkem.githubrepo.data.model.Response
import com.gorkem.githubrepo.data.model.ServiceResult
import retrofit2.HttpException


open class BaseRepository {
    suspend fun <T> call(networkCall: suspend () -> T): ServiceResult<T> {
        return try {
            val response: T = networkCall.invoke() ?: return ServiceResult.error<T>()

            ServiceResult.success(response)
        } catch (e: HttpException) {
            val errorBody = e.response()!!.errorBody()!!.string()
            val errorResponse = Gson().fromJson(errorBody, Response::class.java)
            ServiceResult.error(errorResponse.message)
        } catch (e: Exception) {
            ServiceResult.error() //Butun error buraya dusuyor
        }
    }
}