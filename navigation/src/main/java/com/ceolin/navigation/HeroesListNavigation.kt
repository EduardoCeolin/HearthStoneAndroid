package com.ceolin.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ceolin.core.AppInfo

fun AppCompatActivity.openHeroesList() =
    startActivity(heroesListIntent)

fun Fragment.openHeroesList() =
    startActivity(heroesListIntent)

val appId = AppInfo.params.appId

private val heroesListIntent = Intent("$appId.heroes_list").apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
}