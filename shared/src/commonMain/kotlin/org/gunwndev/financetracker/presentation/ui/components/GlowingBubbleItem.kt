package org.gunwndev.financetracker.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun GlowingBubble(
    size: Dp,
    colorStart: Color,
    colorEnd: Color,
    percentage: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            // Radial Gradient memberikan efek bola 3D melengkung
            .background(
                Brush.radialGradient(
                    colors = listOf(colorStart, colorEnd),
                    radius = size.value * 1.5f // Perbesar radius agar transisi halus
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = percentage,
                color = Color.White,
                fontSize = (size.value / 4).sp, // Ukuran font dinamis menyesuaikan gelembung
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = (size.value / 10).sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}