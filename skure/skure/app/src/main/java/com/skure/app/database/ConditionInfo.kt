package com.skure.app.database

data class ConditionInfo(
    val id: Int = 0,
    val name: String,
    val description: String,
    val symptoms: List<String>,
    val treatments: List<String>,
    val prevention: List<String>
)