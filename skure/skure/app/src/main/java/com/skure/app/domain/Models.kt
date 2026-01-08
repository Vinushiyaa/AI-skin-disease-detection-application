package com.skure.app.domain

data class Product(
    val name: String,
    val url: String
)

data class Disease(
    val id: String,
    val name: String,
    val description: String,
    val firstAid: List<String>,
    val foodChart: List<String>,
    val tags: List<String>,
    val products: List<Product>
)

data class Hospital(
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val distanceKm: Double,
    val phone: String?,
    val hours: String?,
    val specialization: String? = null,
    val placeId: String? = null
)








