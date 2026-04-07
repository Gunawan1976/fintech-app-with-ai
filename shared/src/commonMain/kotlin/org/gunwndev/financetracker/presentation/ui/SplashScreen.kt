package org.gunwndev.financetracker.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import financetrackerwithai.shared.generated.resources.Res
import financetrackerwithai.shared.generated.resources.karakter
import org.gunwndev.financetracker.presentation.ui.theme.Dark1A
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    onSwipeComplete: () -> Unit
){
    val smoothRadialGradient = Brush.radialGradient(
        colorStops = arrayOf(
            0.0f to Color(0xFF4E678B).copy(alpha = 1.0f),
            0.4f to Color(0xFF445A79).copy(alpha = 0.5f),
            0.7f to Color(0xFF404F65).copy(alpha = 0.5f),
            1.0f to Dark1A
        ),
        radius = 1150f
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(smoothRadialGradient),
        containerColor = Color.Transparent.copy(alpha = 0.75f),
    ) {
        val height = 72.dp
        val thumbSize = 72.dp
        val backgroundColor = Color(0xFF1E1E1E) // Abu-abu sangat gelap
        val thumbColor = Color(0xFF8B5CF6) // Ungu terang
        val endIconBackColor = Color(0xFF2C2C2C)

        Column(
            modifier = Modifier.padding(22.dp).fillMaxSize().safeDrawingPadding(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                // Gunakan painterResource dari Jetbrains Compose, BUKAN dari androidx
                painter = painterResource(Res.drawable.karakter),
                contentDescription = "Logo Aplikasi",
                modifier = Modifier.size(500.dp) // Opsional: Atur ukuran gambar
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Take Control")
                    }
                    append(" of Your Finances Today!")
                },
                color = Color.White,
                fontSize = 32.sp,
                lineHeight = 40.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "With our app, you can easily track your income and expenses, and see where your money is going.",
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .clip(CircleShape)
                    .background(backgroundColor)
            ) {
                val density = LocalDensity.current
                // Menghitung jarak maksimal tombol bisa digeser (Lebar total - Lebar tombol)
                val maxDragPx = with(density) { (maxWidth - thumbSize).toPx() }

                var dragOffset by remember { mutableStateOf(0f) }
                val animatedOffset by animateFloatAsState(targetValue = dragOffset, label = "offset_animation")

                // Alpha untuk teks di tengah (semakin digeser, semakin memudar)
                val textAlpha = 1f - (animatedOffset / maxDragPx).coerceIn(0f, 1f)

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(thumbSize)
                        .padding(6.dp)
                        .clip(CircleShape)
                        .background(endIconBackColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Unlocked",
                        tint = Color.White
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(textAlpha)
                        .padding(start = 24.dp), // Sedikit geser ke kanan agar seimbang dengan tombol ungu
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Get Started", // Typo "Satrted" pada gambar sudah diperbaiki
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    // 3 Panah dengan opacity menurun
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = thumbColor.copy(alpha = 0.8f),
                        modifier = Modifier.size(20.dp).offset(x = 8.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = thumbColor.copy(alpha = 0.5f),
                        modifier = Modifier.size(20.dp).offset(x = 4.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = thumbColor.copy(alpha = 0.2f),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .offset { IntOffset(animatedOffset.roundToInt(), 0) }
                        .size(thumbSize)
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures(
                                onDragEnd = {
                                    // Jika digeser lebih dari 75%, anggap selesai
                                    if (dragOffset > maxDragPx * 0.75f) {
                                        dragOffset = maxDragPx
                                        onSwipeComplete()
                                    } else {
                                        // Batal, kembalikan ke awal
                                        dragOffset = 0f
                                    }
                                }
                            ) { change, dragAmount ->
                                change.consume()
                                // Pastikan tombol tidak keluar batas 0 hingga maxDragPx
                                dragOffset = (dragOffset + dragAmount).coerceIn(0f, maxDragPx)
                            }
                        }
                        .padding(6.dp)
                        .clip(CircleShape)
                        .background(thumbColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = "Swipe Right",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
