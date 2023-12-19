package com.antoninovitale.beerpedia.data.network

import com.antoninovitale.beerpedia.common.IDispatcherProvider
import com.antoninovitale.beerpedia.data.network.dto.BeerDTO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val dispatchers: IDispatcherProvider,
    private val service: PunkApiRetrofitService
) {

    suspend fun getBeers(): List<BeerDTO> =
        withContext(dispatchers.io()) {
            service.getBeers()
        }
}
