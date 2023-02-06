package com.ceolin.heroes_list.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroesResponseItem(
    val img: String? = "",
    val cost: Int? = 0,
    val collectible: Boolean,
    val artist: String? = "",
    val health: Int? = 0,
    val mechanics: List<MechanicsItem>? = arrayListOf(),
    val dbfId: Int? = 0,
    val type: String? = "",
    val locale: String? = "",
    val flavor: String? = "",
    val playerClass: String? = "",
    val cardSet: String? = "",
    val attack: Int,
    val cardId: String? = "",
    val name: String? = "",
    val imgGold: String? = "",
    val text: String? = "",
    val rarity: String? = "",
    val spellSchool: String? = "",
    val race: String? = "",
    val howToGetGold: String? = "",
    val howToGet: String? = "",
    val durability: Int,
    val faction: String? = "",
    val classes: List<String>? = arrayListOf(),
    val multiClassGroup: String? = "",
    val elite: Boolean? = false,
    val armor: String? = ""
) : Parcelable

@Parcelize
data class MechanicsItem(
    val name: String
) : Parcelable