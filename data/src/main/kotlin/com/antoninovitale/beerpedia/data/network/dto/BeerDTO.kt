package com.antoninovitale.beerpedia.data.network.dto

import com.google.gson.annotations.SerializedName

data class BeerDTO(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("tagline")
    val tagline: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null,

    @SerializedName("ingredients")
    val ingredients: IngredientsDTO? = IngredientsDTO()
)
