package com.antoninovitale.beerpedia.data

import com.antoninovitale.beerpedia.data.network.dto.BeerDTO
import javax.inject.Inject

class BeerDataFromDtoMapper @Inject constructor(
    private val ingredientsMapper: IngredientDataFromDtoMapper
) {

    @Throws(IllegalStateException::class)
    fun map(dto: BeerDTO): BeerData? =
        if (dto.id == null || dto.name == null || dto.imageUrl == null) null
        else {
            BeerData(
                id = dto.id,
                name = dto.name,
                tagline = dto.tagline.orEmpty(),
                description = dto.description.orEmpty(),
                imageUrl = dto.imageUrl,
                ingredients = IngredientsData(
                    malts = dto.ingredients?.malts.orEmpty().mapNotNull {
                        ingredientsMapper.map(name = it.name, amount = it.amount)
                    },
                    hops = dto.ingredients?.hops.orEmpty().mapNotNull {
                        ingredientsMapper.map(name = it.name, amount = it.amount)
                    },
                    yeast = dto.ingredients?.yeast.orEmpty()
                )
            )
        }
}
