package com.example.ejercisiofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AnimatedScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            AppTheme(isDarkMode = isDarkMode) {
                CombinedAnimationsScreen()
            }
        }
    }
}

@Composable
fun AppTheme(isDarkMode: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme(),
        content = content
    )
}

@Composable
fun CombinedAnimationsScreen() {
    var isExpanded by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(true) }
    var isDarkMode by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.DarkGray else Color.White),
        contentAlignment = Alignment.Center // Centramos todo el contenido
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            // Título de la pantalla
            Text(
                text = "Ejemplos de Animación",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = if (isDarkMode) Color.White else Color.Black
            )

            // Elemento que cambia de tamaño y color
            Box(
                modifier = Modifier
                    .size(if (isExpanded) 150.dp else 100.dp)
                    .background(if (isExpanded) Color.Blue else Color.Red)
                    .clickable { isExpanded = !isExpanded }
            )

            // Botón con AnimatedVisibility
            AnimatedVisibility(
                visible = isButtonVisible,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Button(onClick = { isButtonVisible = false }) {
                    Text("Ocultar botón")
                }
            }

            // Botón para alternar entre modo claro y oscuro
            Button(onClick = { isDarkMode = !isDarkMode }) {
                Text("Alternar Modo Claro/Oscuro")
            }
        }
    }
}
