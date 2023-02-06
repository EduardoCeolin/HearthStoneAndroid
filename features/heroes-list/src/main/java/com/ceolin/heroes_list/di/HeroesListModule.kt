package com.ceolin.heroes_list.di

import com.ceolin.domain.network.buildRetrofit
import com.ceolin.heroes_list.domain.repository.HeroesListRepository
import com.ceolin.heroes_list.domain.repository.api.HeroesListApi
import com.ceolin.heroes_list.domain.usecase.HeroesListInteractor
import com.ceolin.heroes_list.domain.usecase.HeroesListUseCase
import com.ceolin.heroes_list.viewmodel.HeroesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val heroesListModule = module {
    single {
        buildRetrofit(
            `class` = HeroesListApi::class.java
        )
    }
    factory { HeroesListRepository(get()) }
    factory { HeroesListInteractor(get()) } binds arrayOf(
        HeroesListUseCase::class
    )
    viewModel { HeroesListViewModel(get()) }
}
