package com.antoninovitale.beerpedia.data

data class BeerData(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String,
    val ingredients: IngredientsData
)

data class IngredientsData(
    val malts: List<IngredientData>,
    val hops: List<IngredientData>,
    val yeast: String
)

data class IngredientData(
    val name: String,
    val amount: AmountData
) {
    data class AmountData(
        val value: Double,
        val unit: String
    )
}
