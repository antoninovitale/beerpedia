package com.antoninovitale.beerpedia.data.local

import com.antoninovitale.beerpedia.data.IngredientsData
import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity.IngredientEntity
import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity.IngredientEntity.Type
import javax.inject.Inject

class IngredientEntitiesMapper @Inject constructor() {

    fun map(ingredients: IngredientsData): List<IngredientEntity> =
        ingredients.malts.map {
            IngredientEntity.newBuilder()
                .setName(it.name)
                .setAmount(it.amount.value)
                .setUnit(it.amount.unit)
                .setType(Type.MALT)
                .build()
        } + ingredients.hops.map {
            IngredientEntity.newBuilder()
                .setName(it.name)
                .setAmount(it.amount.value)
                .setUnit(it.amount.unit)
                .setType(Type.HOP)
                .build()
        } + IngredientEntity.newBuilder()
            .setName(ingredients.yeast)
            .setType(Type.YEAST)
            .build()
}
