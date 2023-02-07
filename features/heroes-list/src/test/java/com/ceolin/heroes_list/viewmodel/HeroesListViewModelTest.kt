package com.ceolin.heroes_list.viewmodel

import MainCoroutineScopeRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ceolin.domain.network.ErrorResponse
import com.ceolin.domain.network.State
import com.ceolin.heroes_list.base.BaseTest
import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import com.ceolin.heroes_list.domain.usecase.HeroesListUseCase
import com.ceolin.heroes_list.ui.heroeslist.HeroesListViewState
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HeroesListViewModelTest : BaseTest() {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val heroesListUseCase: HeroesListUseCase = mockk()

    private lateinit var heroesListViewModel: HeroesListViewModel

    private val errorResponse: ErrorResponse = mockk()

    private val heroesList = listOf(
        HeroesResponseItem(
            cardId = "cardID",
            dbfId = 1234,
            name = "Name Hero",
            cardSet = "Test",
            type = "Test",
            faction = "Test",
            rarity = "Test",
            cost = 1,
            attack = 1,
            health = 1,
            text = "Test",
            flavor = "Test",
            artist = "Test",
            collectible = false,
            race = "Test",
            playerClass = "Test",
            img = "Test.png",
            locale = "ptBR",
            durability = 0
        )
    )

    @Before
    fun setup() {
        heroesListViewModel = HeroesListViewModel(heroesListUseCase)
    }

    @After
    fun finish() {
        unmockkAll()
    }

    @Test
    fun `when getHeroesList is called, and it's successful, update HeroesListViewState`() {
        // GIVEN
        coEvery { heroesListUseCase.getHeroesList() } returns flow {
            emit(State.Loading())
            emit(State.Success(mapOf("data" to heroesList)))
        }

        val expected = HeroesListViewState.HeroesList(
            isLoading = false,
            heroesList = heroesList
        )

        // WHEN
        heroesListViewModel.getHeroesList()

        // THEN
        heroesListViewModel.viewState.value shouldBe expected
        (heroesListViewModel.viewState.value as HeroesListViewState.HeroesList)
            .heroesList shouldBe expected.heroesList
        (heroesListViewModel.viewState.value as HeroesListViewState.HeroesList)
            .isLoading shouldBe expected.isLoading
        (heroesListViewModel.viewState.value as HeroesListViewState.HeroesList)
            .heroesList?.get(0) shouldBe expected.heroesList?.get(0)
    }

    @Test
    fun `when getHeroesList is called, and it's loading, update HeroesListViewState`() {
        // GIVEN
        coEvery { heroesListUseCase.getHeroesList() } returns flowOf(State.Loading())
        val expected = HeroesListViewState.HeroesList(
            isLoading = true
        )

        // WHEN
        heroesListViewModel.getHeroesList()

        // THEN
        expected shouldBe heroesListViewModel.viewState.value
    }

    @Test
    fun `when getHeroesList is called, and it's error, update HeroesListViewState`() {
        // GIVEN
        coEvery { heroesListUseCase.getHeroesList() } returns flowOf(
            State.Error(
                null,
                400,
                errorResponse
            )
        )
        val expected = HeroesListViewState.HeroesList(
            isError = true,
            throwable = errorResponse
        )

        // WHEN
        heroesListViewModel.getHeroesList()

        // THEN
        expected shouldBe heroesListViewModel.viewState.value
    }
}