package com.ceolin.heroes_list.repository

import com.ceolin.domain.network.ErrorResponse
import com.ceolin.domain.network.State
import com.ceolin.heroes_list.base.BaseTest
import com.ceolin.heroes_list.domain.model.HeroesResponseItem
import com.ceolin.heroes_list.domain.repository.HeroesListRepository
import com.ceolin.heroes_list.domain.repository.api.HeroesListApi
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HeroesListRepositoryTest : BaseTest() {

    private lateinit var mockHearthStoneApi: HeroesListApi
    private lateinit var mockHeroesListRepository: HeroesListRepository
    private lateinit var mockHeroesResponseItem: HeroesResponseItem

    @Before
    fun init() {
        mockHearthStoneApi = mockk(relaxed = true)
        mockHeroesListRepository = HeroesListRepository(
            api = mockHearthStoneApi
        ).toSpy()
        mockHeroesResponseItem = mockk()
    }

    @After
    fun finish() {
        unmockkAll()
    }

    @Test
    fun `test getHeroesList method with success response`() = runTest {
        // GIVEN
        val success = flowOf(State.Success(mapOf<String, List<HeroesResponseItem>>()))
        coEvery { mockHeroesListRepository.getHeroesList() } answers { success }

        // WHEN
        val result = mockHeroesListRepository.getHeroesList()

        // THEN
        result shouldBe success
    }

    @Test
    fun `test getHeroesList method with error response`() = runTest {
        // GIVEN
        val errorResponse = mockk<ErrorResponse>()
        val error = flowOf(State.Error(null, 400, errorResponse))
        coEvery { mockHeroesListRepository.getHeroesList() } answers { error }

        // WHEN
        val result = mockHeroesListRepository.getHeroesList()

        // THEN
        result shouldBe error
    }
}