package com.ceolin.core

object AppInfo {

    lateinit var params: AppParams
        private set

    fun init(init: AppParams.() -> Unit) {
        val params = AppParams()
        params.init()

        if (params.appId.isEmpty()) throw NullPointerException("appId must be set")

        if (params.appVersion.isEmpty()) throw NullPointerException("appVersion must be set")

        if (params.appVersionCode <= 0) throw NullPointerException("appVersionCode must be set")

        if (params.buildType.isEmpty()) throw NullPointerException("buildType must be set")

        if (params.baseUrl.isEmpty()) throw NullPointerException("baseUrl must be set")

        AppInfo.params = params
    }

    data class AppParams(
        var appId: String = "",
        var appVersion: String = "",
        var appVersionCode: Int = 0,
        var buildType: String = "",
        var baseUrl: String = ""
    )
}