package com.antoninovitale.beerpedia.data

import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity
import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity.IngredientEntity.Type
import javax.inject.Inject

class BeerDataFromEntityMapper @Inject constructor(
    private val ingredientMapper: IngredientDataFromEntityMapper
) {

    fun map(entity: BeerEntity): BeerData =
        BeerData(
            id = entity.id,
            name = entity.name,
            tagline = entity.tagline,
            description = entity.description,
            imageUrl = entity.imageUrl,
            ingredients = IngredientsData(
                malts = entity.ingredientsList.filter { it.type == Type.MALT }
                    .map(ingredientMapper::map),
                hops = entity.ingredientsList.filter { it.type == Type.HOP }
                    .map(ingredientMapper::map),
                yeast = entity.ingredientsList.singleOrNull { it.type == Type.YEAST }
                    ?.name.orEmpty()
            )
        )
}
