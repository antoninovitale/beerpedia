package com.antoninovitale.beerpedia.data.network.dto

import com.google.gson.annotations.SerializedName

data class AmountDTO(

    @SerializedName("value")
    val value: Double? = null,

    @SerializedName("unit")
    val unit: String? = null
)
