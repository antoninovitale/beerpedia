package com.antoninovitale.beerpedia.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
internal interface RepositoryModule {

    @Binds
    fun bindRepository(impl: PunkApiRepository): BeerRepository
}
