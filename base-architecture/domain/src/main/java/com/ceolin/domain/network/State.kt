package com.ceolin.domain.network

import com.google.gson.annotations.SerializedName

sealed class State<out T>(val data: T?) {
    class Success<T>(data: T) : State<T>(data)
    class Loading<T>() : State<T>(null)
    class Error<T>(data: T? = null, val code: Int, val error: ErrorResponse?) : State<T>(data)
}

sealed class ErrorResponse {

    data class ServerErrorResponse(
        @SerializedName("message") val message: String,
    ) : ErrorResponse()

    object NetworkErrorResponse : ErrorResponse()

    object GenericErrorResponse : ErrorResponse()
}