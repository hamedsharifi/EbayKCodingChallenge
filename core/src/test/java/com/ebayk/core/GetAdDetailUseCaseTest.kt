package com.ebayk.core

import com.ebayk.core.model.Ad
import com.ebayk.core.repository.AdRepository
import com.ebayk.core.usecase.GetAdDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import ir.haytech.core.testutils.MainCoroutineRuleCore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class GetAdDetailUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRuleCore()

    @RelaxedMockK
    lateinit var repository: AdRepository

    @RelaxedMockK
    lateinit var ad: Ad

    private fun createUseCase() = GetAdDetailUseCase(
        repository = repository
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when getting ad id 1118635128 details UseCase should return Ad`() =
        mainCoroutineRule.runBlockingTest {
            val useCase = createUseCase()

            coEvery {
                delay(100)
                repository.getAdDetail("1118635128")
            } coAnswers { ad }

            val response = useCase.execute("1118635128")

            delay(200)

            coVerify(exactly = 1) {
                repository.getAdDetail("1118635128")
            }
            kotlin.test.assertEquals(ad, response)
        }

}