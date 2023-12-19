package com.antoninovitale.beerpedia.common

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DispatcherProviderModule {

    @Binds
    fun bindDispatcherProvider(impl: DispatcherProvider): IDispatcherProvider
}
