package com.skure.app.domain

data class UserProfile(
    val id: Int = 0,
    val fullName: String,
    val email: String,
    val phone: String,
    val medicalHistory: List<String> = emptyList(),
    val otherHistory: String = ""
)


