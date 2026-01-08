package com.skure.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SkureDarkScheme = darkColorScheme()

@Composable
fun SkureTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SkureDarkScheme,
        content = content
    )
}



