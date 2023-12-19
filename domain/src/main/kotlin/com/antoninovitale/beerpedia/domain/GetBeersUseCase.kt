package com.antoninovitale.beerpedia.domain

import com.antoninovitale.beerpedia.common.IDispatcherProvider
import com.antoninovitale.beerpedia.data.BeerRepository
import com.antoninovitale.beerpedia.domain.mapper.BeersMapper
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IGetBeersUseCase {

    suspend fun execute(): List<Beer>
}

class GetBeersUseCase @Inject constructor(
    private val dispatchers: IDispatcherProvider,
    private val repository: BeerRepository,
    private val mapper: BeersMapper
) : IGetBeersUseCase {

    override suspend fun execute(): List<Beer> =
        withContext(dispatchers.io()) {
            mapper.map(repository.getBeers())
        }
}
