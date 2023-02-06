package com.ceolin.hearthstoneandroid

import android.app.Application
import com.ceolin.core.AppInfo
import com.ceolin.heroes_list.di.heroesListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HearthStoneApp : Application() {
    override fun onCreate() {
        super.onCreate()

        AppInfo.init {
            appId = BuildConfig.APPLICATION_ID
            appVersion = BuildConfig.VERSION_NAME
            appVersionCode = BuildConfig.VERSION_CODE
            buildType = BuildConfig.BUILD_TYPE
            baseUrl = BuildConfig.BASE_URL
        }

        startKoin {
            androidContext(this@HearthStoneApp)
            modules(
                listOf(
                    heroesListModule
                )
            )
        }
    }
}