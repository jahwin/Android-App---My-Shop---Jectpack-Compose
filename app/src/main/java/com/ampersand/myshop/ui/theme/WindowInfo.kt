package com.ampersand.myshop.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val windowWidth: Dp,
    val windowHeight: Dp
) {
     sealed class WindowType {
         data object Small : WindowType()
         data object Medium : WindowType()
         data object Large : WindowType()
     }
}

@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    return WindowInfo(
        screenWidthInfo = when {
            configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Small
            configuration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Large
        },
        screenHeightInfo = when {
            configuration.screenHeightDp < 600 -> WindowInfo.WindowType.Small
            configuration.screenHeightDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Large
        },
        windowWidth = configuration.screenWidthDp.dp,
        windowHeight = configuration.screenHeightDp.dp
    )
}