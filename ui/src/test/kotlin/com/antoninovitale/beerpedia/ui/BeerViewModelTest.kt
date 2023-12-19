package com.antoninovitale.beerpedia.ui

import app.cash.turbine.test
import com.antoninovitale.beerpedia.domain.IGetBeersUseCase
import com.antoninovitale.common.tests.CommonUnitTest
import com.antoninovitale.common.tests.sampleListOfBeers
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class BeerViewModelTest : CommonUnitTest<BeerViewModel>() {

    @Mock
    lateinit var mockUseCase: IGetBeersUseCase

    override fun sut() =
        BeerViewModel(dispatchers, mockUseCase)

    @Test
    fun `loads beers on start`() = runTest {
        clearInvocations(mockUseCase)
        val expected = sampleListOfBeers()
        whenever(mockUseCase.execute()).thenReturn(expected)

        sut.state.test {
            skipItems(1)

            sut.start()

            assertEquals(UiState.Loaded(expected), awaitItem())
            expectNoEvents()
        }
        verify(mockUseCase).execute()
        verifyNoMoreInteractions(mockUseCase)
    }

    @Test
    fun `fails to load beers on start`() = runTest {
        clearInvocations(mockUseCase)
        whenever(mockUseCase.execute()).thenThrow(RuntimeException("Loading failed!"))

        sut.state.test {
            sut.start()

            assertEquals(UiState.Error, awaitItem())
            expectNoEvents()
        }
        verify(mockUseCase).execute()
        verifyNoMoreInteractions(mockUseCase)
    }
}
