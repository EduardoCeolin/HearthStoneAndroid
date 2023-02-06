package com.ceolin.heroes_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceolin.domain.network.State
import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import com.ceolin.heroes_list.domain.usecase.HeroesListUseCase
import com.ceolin.heroes_list.ui.heroeslist.HeroesListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HeroesListViewModel(private val heroesListUseCase: HeroesListUseCase) : ViewModel() {

    private val viewModelState = MutableStateFlow<HeroesListViewState>(
        value = HeroesListViewState.Initial()
    )

    val viewState: StateFlow<HeroesListViewState> = viewModelState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = viewModelState.value
    )

    init {
        getHeroesList()
    }

    fun getHeroesList() =
        viewModelScope.launch {
            heroesListUseCase.getHeroesList().collect { result ->
                when (result) {
                    is State.Loading -> {
                        viewModelState.update {
                            HeroesListViewState.HeroesList(
                                isLoading = true
                            )
                        }
                    }
                    is State.Success -> {
                        viewModelState.update {
                            HeroesListViewState.HeroesList(
                                isLoading = false,
                                heroesList = getHeroesWithImage(result.data)
                            )
                        }
                    }
                    is State.Error -> {
                        viewModelState.update {
                            HeroesListViewState.HeroesList(
                                isError = true, throwable = result.error
                            )
                        }
                    }
                }
            }
        }

    private fun getHeroesWithImage(heroesResponse: Map<String, List<HeroesResponseItem>>?): List<HeroesResponseItem> {
        val heroesList = mutableListOf<HeroesResponseItem>()
        heroesResponse?.values?.map {
            it.map { hero ->
                if (hero.img != null) {
                    heroesList.add(hero)
                }
            }
        }

        return heroesList
    }
}