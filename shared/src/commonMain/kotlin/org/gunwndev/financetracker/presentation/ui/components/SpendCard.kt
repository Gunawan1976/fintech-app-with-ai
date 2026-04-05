package org.gunwndev.financetracker.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpendeesCard(modifier: Modifier = Modifier) {
    // 1. Definisi Warna Gelap & Warna Gradient Gelembung
    val CardBg = Color(0xFF141416) // Latar hitam/abu sangat gelap

    // Warna Gelembung (Pusat Terang -> Tepi Gelap)
    val PurpleLight = Color(0xFF8B5CF6)
    val PurpleDark = Color(0xFF4C1D95)

    val CyanLight = Color(0xFF22D3EE)
    val CyanDark = Color(0xFF0891B2)

    val PinkLight = Color(0xFFF472B6)
    val PinkDark = Color(0xFFBE185D)

    // 2. Bentuk Kartu (Sudut potong asimetris)
    val cardShape = RoundedCornerShape(
        topStart = 32.dp, // Potongan besar kiri atas
        topEnd = 16.dp,
        bottomStart = 16.dp,
        bottomEnd = 32.dp  // Potongan besar kanan bawah
    )

    Box(
        modifier = modifier
            .clip(cardShape)
            .background(CardBg)
            .padding(24.dp)
    ) {
        Column {
            // --- HEADER ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Placeholder Logo Kecil (Bisa ganti pakai Image)
                Box(modifier = Modifier.size(16.dp).background(Color.Green, RoundedCornerShape(4.dp)))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SPENDEES",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- AREA GELEMBUNG (BUBBLES) ---
            // Menggunakan Box agar bisa tumpang tindih (overlap)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentAlignment = Alignment.Center
            ) {
                // Gelembung 1: ESSENTIALS (Paling Besar, di belakang/tengah)
                GlowingBubble(
                    size = 140.dp,
                    colorStart = PurpleLight,
                    colorEnd = PurpleDark,
                    percentage = "57%",
                    label = "ESSENTIALS",
                    modifier = Modifier.offset(y = (-20).dp)
                )

                // Gelembung 2: LIFESTYLE (Sedang, di Kanan Bawah)
                GlowingBubble(
                    size = 100.dp,
                    colorStart = CyanLight,
                    colorEnd = CyanDark,
                    percentage = "28%",
                    label = "LIFESTYLE",
                    modifier = Modifier.offset(x = 50.dp, y = 50.dp) // Geser ke kanan bawah
                )

                // Gelembung 3: IMPULSE (Kecil, di Kiri Bawah)
                GlowingBubble(
                    size = 70.dp,
                    colorStart = PinkLight,
                    colorEnd = PinkDark,
                    percentage = "15%",
                    label = "IMPULSE",
                    modifier = Modifier.offset(x = (-60).dp, y = 40.dp) // Geser ke kiri bawah
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- FOOTER ---
            Text(
                text = "TOTAL SPENDINGS",
                color = Color.LightGray,
                fontSize = 12.sp
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "7,600",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Indikator persentase kecil
                Text(
                    text = "▲ 2.5%",
                    color = Color(0xFFEF4444), // Merah
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
        }
    }
}

// --- FUNGSI PEMBANTU UNTUK GELEMBUNG ---
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