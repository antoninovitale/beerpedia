package com.antoninovitale.beerpedia.data.network

import com.antoninovitale.beerpedia.data.network.dto.BeerDTO
import retrofit2.http.GET

interface PunkApiRetrofitService {

    @GET("v2/beers")
    suspend fun getBeers(): List<BeerDTO>
}
