package com.antoninovitale.beerpedia.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.antoninovitale.beerpedia.common.IDispatcherProvider
import com.antoninovitale.beerpedia.data.BeerData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val Context.beersDataStore: DataStore<BeerListEntity> by dataStore(
    fileName = "beers.pb",
    serializer = BeerListEntitySerializer
)

class LocalDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val dispatchers: IDispatcherProvider,
    private val beerEntityMapper: BeerEntityMapper
) {

    fun getBeers(): Flow<BeerListEntity> =
        context.beersDataStore.data

    suspend fun saveBeers(beers: List<BeerData>) =
        withContext(dispatchers.io()) {
            context.beersDataStore.updateData {
                it.toBuilder()
                    .clearBeers()
                    .addAllBeers(beers.map(beerEntityMapper::map))
                    .build()
            }
        }
}
