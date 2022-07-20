package com.hashconcepts.customdatepickerdialog.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val spinnerGradient = Brush.linearGradient(
    0.0f to Color(0xFFFFFFFF),
    0.3f to Color(0x00000000),
    0.5f to Color(0x00000000),
    0.7f to Color(0x00000000),
    1.0f to Color(0xFFFFFFFF),
    start = Offset(0.0f, -13.0f),
    end = Offset(0.0f, 320.0f)
)