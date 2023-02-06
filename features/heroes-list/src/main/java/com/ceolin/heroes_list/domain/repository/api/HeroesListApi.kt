package com.ceolin.heroes_list.domain.repository.api

import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import retrofit2.http.GET

interface HeroesListApi {

    @GET("cards?locale=ptBR")
    suspend fun getHeroesList(): Map<String, List<HeroesResponseItem>>

}