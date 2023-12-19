package com.antoninovitale.beerpedia.data.network.dto

import com.google.gson.annotations.SerializedName

data class MaltDTO(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("amount")
    val amount: AmountDTO? = AmountDTO()
)
