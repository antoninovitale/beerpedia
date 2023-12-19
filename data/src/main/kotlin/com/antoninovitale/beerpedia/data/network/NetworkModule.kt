package com.antoninovitale.beerpedia.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ViewModelComponent::class)
@Module
object NetworkModule {

    private const val API_BASE_URL = "https://api.punkapi.com"

    @Provides
    fun provideGson(): Gson =
        GsonBuilder().create()

    @Provides
    fun provideRetrofitService(gson: Gson): PunkApiRetrofitService =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PunkApiRetrofitService::class.java)
}
