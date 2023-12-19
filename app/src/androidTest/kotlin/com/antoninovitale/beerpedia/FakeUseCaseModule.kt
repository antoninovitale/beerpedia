package com.antoninovitale.beerpedia

import com.antoninovitale.beerpedia.domain.Beer
import com.antoninovitale.beerpedia.domain.GetBeersUseCaseModule
import com.antoninovitale.beerpedia.domain.IGetBeersUseCase
import com.antoninovitale.common.tests.sampleListOfBeers
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [GetBeersUseCaseModule::class]
)
@Module
object FakeUseCaseModule {

    @Singleton
    @Provides
    fun provideFakeUseCase() = object : IGetBeersUseCase {
        override suspend fun execute(): List<Beer> =
            sampleListOfBeers()
    }
}
