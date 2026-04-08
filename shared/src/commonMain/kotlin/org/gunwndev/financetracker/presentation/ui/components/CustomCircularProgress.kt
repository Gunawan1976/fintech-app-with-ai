package org.gunwndev.financetracker.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressWithText(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.size(60.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 6.dp.toPx()
            // Background Circle (Abu-abu gelap)
            drawCircle(
                color = Color(0xFF1E2321),
                style = Stroke(width = strokeWidth)
            )
            // Progress Arc (Gradasi Hijau ke Biru)
            drawArc(
                brush = Brush.sweepGradient(
                    listOf(Color(0xFF4CAF50), Color(0xFF3F51B5), Color(0xFF4CAF50))
                ),
                startAngle = -90f,
                sweepAngle = 360 * progress,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
        println("isi dari ini ${progress}")
        Text(
            text = "${(progress * 100).toInt()}%",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace, // Atau font pixel kamu
                color = Color.White
            )
        )
    }
}