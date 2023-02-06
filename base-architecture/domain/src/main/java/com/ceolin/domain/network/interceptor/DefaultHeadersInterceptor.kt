package com.ceolin.domain.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class DefaultHeadersInterceptors : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .addHeader(
                "X-RapidAPI-Key",
                "ded5d76c7dmshf34764e4440f8dep1b1eb5jsn4d418e5b9f60"
            )
            .addHeader(
                "X-RapidAPI-Host",
                "omgvamp-hearthstone-v1.p.rapidapi.com"
            )

        return chain.proceed(builder.build())
    }

}