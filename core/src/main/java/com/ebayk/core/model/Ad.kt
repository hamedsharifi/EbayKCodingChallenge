package com.ebayk.core.model

import com.google.gson.annotations.SerializedName

data class Ad (

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Price,
    @SerializedName("visits") val visits : Int,
    @SerializedName("address") val address : Address,
    @SerializedName("posted-date-time") val postedDateTime : String,
    @SerializedName("description") val description : String?,
    @SerializedName("attributes") val attributes : List<Attributes>?,
    @SerializedName("features") val features : List<String>?,
    @SerializedName("pictures") val pictures : List<String>,
    @SerializedName("documents") val documents : List<Documents>?
)

data class Address (

    @SerializedName("street") val street : String,
    @SerializedName("city") val city : String,
    @SerializedName("zip-code") val zipCode : Int,
    @SerializedName("longitude") val longitude : Double,
    @SerializedName("latitude") val latitude : Double
)

data class Price (

    @SerializedName("currency") val currency : String,
    @SerializedName("amount") val amount : Int
)

data class Documents (

    @SerializedName("link") val link : String,
    @SerializedName("title") val title : String
)

data class Attributes (

    @SerializedName("label") val label : String,
    @SerializedName("value") val value : String,
    @SerializedName("unit") val unit : String?
)
