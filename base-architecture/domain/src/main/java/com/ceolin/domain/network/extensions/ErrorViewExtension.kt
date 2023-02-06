package com.ceolin.domain.network.extensions

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ceolin.domain.network.ErrorResponse

fun Activity.showApiError(error: ErrorResponse?) {
    baseContext.showApiError(error)
}

fun Fragment.showApiError(error: ErrorResponse?) {
    requireContext().showApiError(error)
}

fun Context.showApiError(error: ErrorResponse?) {
    when (error) {
        is ErrorResponse.ServerErrorResponse -> {
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        }
        is ErrorResponse.NetworkErrorResponse -> {
            Toast.makeText(
                this,
                "Houve um erro de conexão com a internet. Por favor verifique sua conexão com a internet.",
                Toast.LENGTH_LONG
            ).show()
        }
        else -> {
            Toast.makeText(
                this,
                "Ocorreu um erro inesperado, por favor tente novamente mais tarde.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}