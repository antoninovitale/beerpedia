package com.antoninovitale.common.tests

import com.antoninovitale.beerpedia.data.BeerData
import com.antoninovitale.beerpedia.data.IngredientData
import com.antoninovitale.beerpedia.data.IngredientsData
import com.antoninovitale.beerpedia.data.network.dto.BeerDTO
import com.antoninovitale.beerpedia.domain.Beer
import com.antoninovitale.beerpedia.domain.Ingredient

fun sampleBeerData(id: Int = 0): BeerData =
    BeerData(
        id = id,
        name = "Beer $id",
        tagline = "Tagline $id",
        description = "Description $id",
        imageUrl = "https://image.url",
        ingredients = IngredientsData(
            malts = listOf(
                IngredientData(
                    name = "Malt $id",
                    amount = IngredientData.AmountData(value = 50.0, unit = "grams")
                )
            ),
            hops = listOf(
                IngredientData(
                    name = "Hop $id",
                    amount = IngredientData.AmountData(value = 10.0, unit = "kilograms")
                )
            ),
            yeast = "Yeast $id"
        )
    )

fun sampleListOfBeerDTOs() =
    listOf(BeerDTO(id = 1), BeerDTO(id = 2), BeerDTO(id = 3))

fun sampleBeer(id: Int = 0): Beer =
    Beer(
        id = id,
        name = "Beer $id",
        tagline = "Tagline $id",
        description = "Description $id",
        imageUrl = "https://image.url",
        ingredients = listOf(
            Ingredient.Malt(name = "Malt $id", amount = "50 grams"),
            Ingredient.Hop(name = "Hop $id", amount = "10 kilograms"),
            Ingredient.Yeast(name = "Yeast $id")
        )
    )

fun sampleListOfBeers(): List<Beer> =
    listOf(sampleBeer(id = 1), sampleBeer(id = 2), sampleBeer(id = 3))
