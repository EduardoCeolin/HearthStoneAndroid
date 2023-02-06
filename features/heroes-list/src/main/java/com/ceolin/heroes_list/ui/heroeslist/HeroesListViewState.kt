package com.ceolin.heroes_list.ui.heroeslist

import com.ceolin.domain.network.ErrorResponse
import com.ceolin.heroes_list.domain.model.HeroesResponseItem

sealed interface HeroesListViewState {

    val isLoading: Boolean
    val isError: Boolean
    val throwable: ErrorResponse?

    data class Initial(
        override val isLoading: Boolean = false,
        override val isError: Boolean = false,
        override val throwable: ErrorResponse? = null
    ) : HeroesListViewState


    data class HeroesList(
        override val isLoading: Boolean = false,
        override val isError: Boolean = false,
        override val throwable: ErrorResponse? = null,
        val heroesList: List<HeroesResponseItem>? = null
    ) : HeroesListViewState
}