package com.antoninovitale.beerpedia.data

import com.antoninovitale.beerpedia.data.local.BeerListEntity.BeerEntity.IngredientEntity
import javax.inject.Inject

class IngredientDataFromEntityMapper @Inject constructor() {

    fun map(entity: IngredientEntity): IngredientData =
        IngredientData(
            name = entity.name,
            amount = IngredientData.AmountData(
                value = entity.amount,
                unit = entity.unit
            )
        )
}
