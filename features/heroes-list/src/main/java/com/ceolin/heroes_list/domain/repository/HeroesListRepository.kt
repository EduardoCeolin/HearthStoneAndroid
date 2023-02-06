package com.ceolin.heroes_list.domain.repository

import com.ceolin.domain.network.safeApiCall
import com.ceolin.heroes_list.domain.repository.api.HeroesListApi

class HeroesListRepository(private val api: HeroesListApi) {
    suspend fun getHeroesList() = safeApiCall {
        api.getHeroesList()
    }
}