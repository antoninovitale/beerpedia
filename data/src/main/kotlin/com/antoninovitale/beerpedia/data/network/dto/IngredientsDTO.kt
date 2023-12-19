package com.antoninovitale.beerpedia.data.network.dto

import com.google.gson.annotations.SerializedName

data class IngredientsDTO(

    @SerializedName("malt")
    val malts: List<MaltDTO> = listOf(),

    @SerializedName("hops")
    val hops: List<HopsDTO> = listOf(),

    @SerializedName("yeast")
    val yeast: String? = null
)
