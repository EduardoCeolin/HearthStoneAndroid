package com.ceolin.domain.network

import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T) =
    flow {
        emit(State.Loading())
        emit(State.Success(apiCall.invoke()))
    }.catch {
        val error = when (it) {
            is IOException -> State.Error(
                data = null,
                code = 0,
                error = ErrorResponse.NetworkErrorResponse
            )
            is HttpException -> {
                val code = it.code()
                val errorResponse = convertErrorBody(it)
                State.Error(data = null, code = code, error = errorResponse)
            }
            else -> State.Error(data = null, code = -1, error = ErrorResponse.GenericErrorResponse)
        }
        emit(error)
    }


private fun convertErrorBody(throwable: HttpException): ErrorResponse.ServerErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.string()?.let {
            Gson().fromJson(it, ErrorResponse.ServerErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}