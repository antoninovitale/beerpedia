package com.antoninovitale.beerpedia.data.local

import com.antoninovitale.beerpedia.data.BeerData
import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity
import javax.inject.Inject

class BeerEntityMapper @Inject constructor(
    private val ingredientsMapper: IngredientEntitiesMapper
) {

    fun map(beer: BeerData): BeerEntity =
        BeerEntity.newBuilder()
            .setId(beer.id)
            .setName(beer.name)
            .setTagline(beer.tagline)
            .setDescription(beer.description)
            .setImageUrl(beer.imageUrl)
            .addAllIngredients(ingredientsMapper.map(beer.ingredients))
            .build()
}
