package com.antoninovitale.beerpedia.data

import com.antoninovitale.beerpedia.data.local.BeerListEntity
import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity
import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity.IngredientEntity
import com.antoninovitale.beerpedia.data.local.LocalDataSource
import com.antoninovitale.beerpedia.data.network.RemoteDataSource
import com.antoninovitale.common.tests.CommonUnitTest
import com.antoninovitale.common.tests.sampleBeerData
import com.antoninovitale.common.tests.sampleListOfBeerDTOs
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class PunkApiRepositoryTest : CommonUnitTest<PunkApiRepository>() {

    @Mock
    lateinit var mockLocalDataSource: LocalDataSource

    @Mock
    lateinit var mockRemoteDataSource: RemoteDataSource

    @Mock
    lateinit var mockBeerFromEntityMapper: BeerDataFromEntityMapper

    @Mock
    lateinit var mockBeerFromDtoMapper: BeerDataFromDtoMapper

    override fun sut() =
        PunkApiRepository(
            dispatchers,
            mockLocalDataSource,
            mockRemoteDataSource,
            mockBeerFromEntityMapper,
            mockBeerFromDtoMapper
        )

    @Test
    fun `gets beers from local data source`() = runTest {
        val beerListEntity = sampleBeerListEntity()
        whenever(mockLocalDataSource.getBeers()).thenReturn(flowOf(beerListEntity))
        val expected = beerListEntity.beersList.map {
            val beer = sampleBeerData(it.id)
            whenever(mockBeerFromEntityMapper.map(it)).thenReturn(beer)
            beer
        }

        val actual = sut().getBeers()

        assertEquals(expected, actual)
        verify(mockLocalDataSource).getBeers()
        verify(mockBeerFromEntityMapper).map(beerListEntity.beersList[0])
        verifyNoMoreInteractions(mockLocalDataSource, mockBeerFromEntityMapper)
        verifyNoInteractions(mockBeerFromDtoMapper, mockRemoteDataSource)
    }

    @Test
    fun `gets beers from remote data source when local data source is empty`() = runTest {
        whenever(mockLocalDataSource.getBeers()).thenReturn(emptyFlow())
        val beerDTOs = sampleListOfBeerDTOs()
        whenever(mockRemoteDataSource.getBeers()).thenReturn(beerDTOs)
        val expected = beerDTOs.map {
            val beer = sampleBeerData(it.id!!) //id is the only property set.
            whenever(mockBeerFromDtoMapper.map(it)).thenReturn(beer)
            beer
        }

        val actual = sut().getBeers()

        assertEquals(expected, actual)
        verify(mockLocalDataSource).getBeers()
        verify(mockRemoteDataSource).getBeers()
        verify(mockLocalDataSource).saveBeers(expected)
        verify(mockBeerFromDtoMapper).map(beerDTOs[0])
        verify(mockBeerFromDtoMapper).map(beerDTOs[1])
        verify(mockBeerFromDtoMapper).map(beerDTOs[2])
        verifyNoMoreInteractions(mockLocalDataSource, mockRemoteDataSource, mockBeerFromDtoMapper)
        verifyNoInteractions(mockBeerFromEntityMapper)
    }

    @Test
    fun `gets beers from remote data source, but list is empty`() = runTest {
        whenever(mockLocalDataSource.getBeers()).thenReturn(emptyFlow())
        whenever(mockRemoteDataSource.getBeers()).thenReturn(emptyList())

        val actual = sut().getBeers()

        assertEquals(emptyList(), actual)
        verify(mockLocalDataSource).getBeers()
        verify(mockRemoteDataSource).getBeers()
        verifyNoMoreInteractions(mockLocalDataSource, mockRemoteDataSource)
        verifyNoInteractions(mockBeerFromEntityMapper, mockBeerFromDtoMapper)
    }
}

private fun sampleBeerListEntity(): BeerListEntity =
    BeerListEntity.newBuilder()
        .addBeers(
            BeerEntity.newBuilder()
                .setId(1)
                .setName("Beer")
                .setTagline("Tagline")
                .setDescription("Description")
                .setImageUrl("https://image.url")
                .addIngredients(IngredientEntity.getDefaultInstance())
                .build()
        )
        .build()
