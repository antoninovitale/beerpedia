package com.antoninovitale.beerpedia.domain.mapper

import com.antoninovitale.beerpedia.data.BeerData
import com.antoninovitale.beerpedia.domain.Beer
import javax.inject.Inject

class BeerMapper @Inject constructor(
    private val ingredientsMapper: IngredientsMapper
) {

    fun map(beer: BeerData): Beer =
        Beer(
            id = beer.id,
            name = beer.name,
            tagline = beer.tagline,
            description = beer.description,
            imageUrl = beer.imageUrl,
            ingredients = ingredientsMapper.map(beer.ingredients)
        )
}
