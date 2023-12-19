package com.antoninovitale.beerpedia.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface GetBeersUseCaseModule {

    @Binds
    fun bindUseCase(impl: GetBeersUseCase): IGetBeersUseCase
}
