package com.ceolin.domain.network

import com.ceolin.core.AppInfo
import com.ceolin.domain.network.interceptor.DefaultHeadersInterceptors
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun <T> buildRetrofit(
    baseUrl: String = AppInfo.params.baseUrl,
    `class`: Class<T>,
    timeout: Long = 180L,
    networkInterceptor: Interceptor? = null
): T =
    Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(buildOkHttpClient(timeout, networkInterceptor))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(`class`)

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private fun buildOkHttpClient(
    timeout: Long,
    networkInterceptor: Interceptor? = null
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .writeTimeout(timeout, TimeUnit.SECONDS)
        .addInterceptor(provideLoggingInterceptor())
        .addInterceptor(DefaultHeadersInterceptors())

    if (networkInterceptor != null) builder.addNetworkInterceptor(networkInterceptor)

    return builder.build()
}