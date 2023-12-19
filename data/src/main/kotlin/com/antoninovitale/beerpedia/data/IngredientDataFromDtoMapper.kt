package com.antoninovitale.beerpedia.data

import com.antoninovitale.beerpedia.data.network.dto.AmountDTO
import javax.inject.Inject

class IngredientDataFromDtoMapper @Inject constructor() {

    fun map(
        name: String?,
        amount: AmountDTO?
    ): IngredientData? =
        if (name != null && amount != null) {
            IngredientData(
                name = name,
                amount = IngredientData.AmountData(
                    value = amount.value ?: 0.0,
                    unit = amount.unit.orEmpty()
                )
            )
        } else null
}
