package com.antoninovitale.beerpedia.domain.mapper

import com.antoninovitale.beerpedia.data.BeerData
import com.antoninovitale.beerpedia.domain.Beer
import javax.inject.Inject

class BeersMapper @Inject constructor(
    private val beerMapper: BeerMapper
) {

    fun map(beers: List<BeerData>): List<Beer> =
        beers.map { beer -> beerMapper.map(beer) }
}
