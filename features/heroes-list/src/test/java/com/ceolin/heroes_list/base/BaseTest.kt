package com.ceolin.heroes_list.base

import io.mockk.spyk

abstract class BaseTest {
    companion object {
        inline fun <reified T : Any> T.toSpy(): T = spyk(this)
    }
}