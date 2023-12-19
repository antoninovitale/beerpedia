package com.antoninovitale.beerpedia.data

import com.antoninovitale.beerpedia.common.IDispatcherProvider
import com.antoninovitale.beerpedia.data.local.LocalDataSource
import com.antoninovitale.beerpedia.data.network.RemoteDataSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface BeerRepository {
    suspend fun getBeers(): List<BeerData>
}

internal class PunkApiRepository @Inject constructor(
    private val dispatchers: IDispatcherProvider,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val beerDataFromEntityMapper: BeerDataFromEntityMapper,
    private val beerDataFromDtoMapper: BeerDataFromDtoMapper
): BeerRepository {

    /**
     * @return beers stored in local data source if any, fetched from remote data source otherwise.
     */
    override suspend fun getBeers(): List<BeerData> =
        withContext(dispatchers.io()) {
            val localData = localDataSource.getBeers().firstOrNull()
            if (localData != null && localData.beersList.isNotEmpty()) {
                localData.beersList.map(beerDataFromEntityMapper::map)
            } else {
                val beers = remoteDataSource.getBeers()
                val list = beers.mapNotNull { beerDataFromDtoMapper.map(it) }
                if (list.isNotEmpty()) localDataSource.saveBeers(list)
                list
            }
        }
}
