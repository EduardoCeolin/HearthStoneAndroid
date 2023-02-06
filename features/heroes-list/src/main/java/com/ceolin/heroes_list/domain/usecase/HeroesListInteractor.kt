package com.ceolin.heroes_list.domain.usecase

import com.ceolin.domain.network.State
import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import com.ceolin.heroes_list.domain.repository.HeroesListRepository
import kotlinx.coroutines.flow.Flow

class HeroesListInteractor(private val repository: HeroesListRepository) : HeroesListUseCase {
    override suspend fun getHeroesList(): Flow<State<Map<String, List<HeroesResponseItem>>>> =
        repository.getHeroesList()
}