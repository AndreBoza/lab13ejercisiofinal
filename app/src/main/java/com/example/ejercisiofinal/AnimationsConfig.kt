package com.example.ejercisiofinal

import androidx.compose.animation.core.tween

val defaultAnimationSpec = tween<Float>(
    durationMillis = 1000,
    easing = { it } // Linear easing
)
