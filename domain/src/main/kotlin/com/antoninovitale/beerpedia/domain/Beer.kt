package com.antoninovitale.beerpedia.domain

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String,
    val ingredients: List<Ingredient>
)

sealed class Ingredient {

    data class Malt(val name: String, val amount: String) : Ingredient()
    data class Hop(val name: String, val amount: String) : Ingredient()
    data class Yeast(val name: String) : Ingredient()
}
