package com.skure.app.database

data class HospitalInfo(
    val id: Long = 0,
    val userId: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String?,
    val distance: Double?,
    val timestamp: Long = System.currentTimeMillis()
)