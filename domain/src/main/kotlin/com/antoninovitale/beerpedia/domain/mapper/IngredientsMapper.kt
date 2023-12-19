package com.antoninovitale.beerpedia.domain.mapper

import com.antoninovitale.beerpedia.data.IngredientData
import com.antoninovitale.beerpedia.data.IngredientsData
import com.antoninovitale.beerpedia.domain.Ingredient
import javax.inject.Inject

class IngredientsMapper @Inject constructor() {

    fun map(ingredients: IngredientsData): List<Ingredient> {
        val hops: List<Ingredient.Hop> =
            ingredients.hops.map {
                Ingredient.Hop(
                    name = it.name,
                    amount = it.toAmount()
                )
            }
        val malts: List<Ingredient.Malt> =
            ingredients.malts.map {
                Ingredient.Malt(
                    name = it.name,
                    amount = it.toAmount()
                )
            }
        val yeast = Ingredient.Yeast(
            name = ingredients.yeast
        )

        return hops + malts + yeast
    }

    private fun IngredientData.toAmount() =
        "${amount.value} ${amount.unit}"
}
