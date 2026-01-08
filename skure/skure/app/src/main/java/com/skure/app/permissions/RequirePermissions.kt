package com.skure.app.permissions

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequirePermissions(
    permissions: List<String>,
    rationale: String,
    content: @Composable () -> Unit
) {
    val perms = rememberMultiplePermissionsState(permissions)
    LaunchedEffect(Unit) { perms.launchMultiplePermissionRequest() }
    when {
        perms.allPermissionsGranted -> content()
        perms.shouldShowRationale -> Text(rationale)
        else -> Button(onClick = { perms.launchMultiplePermissionRequest() }) { Text("Grant permissions") }
    }
}



