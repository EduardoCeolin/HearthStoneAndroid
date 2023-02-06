package com.ceolin.heroes_list.domain.usecase

import com.ceolin.domain.network.State
import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import kotlinx.coroutines.flow.Flow

interface HeroesListUseCase {
    suspend fun getHeroesList(): Flow<State<Map<String, List<HeroesResponseItem>>>>
}