package org.gunwndev.financetracker.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoalsTrackerCard(
    percentage: Int = 73,
    currentSaved: String = "15,750",
    targetSaved: String = "35,500",
    modifier: Modifier = Modifier,
    height: Dp?,
    fontSize: TextUnit?
) {
    // 1. Definisi Warna
    val CardBackground = Color(0xFF141915)
    val GreenGlow = Color(0xFF1E3A21)
    val ActiveGreen = Color(0xFF39FF14) // Hijau neon terang
    val InactiveGreen = Color(0xFF253825) // Hijau sangat gelap
    val TextGrey = Color(0xFFA0A0A0)

    // 2. Bentuk Kartu (Sudut kanan atas lebih tumpul / chamfered)
    val cardShape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 50.dp, // Potongan lebih besar di kanan atas
        bottomEnd = 16.dp,
        bottomStart = 16.dp
    )

    // Latar belakang dengan sedikit gradasi (Glow hijau di tengah atas)
    val backgroundBrush = Brush.radialGradient(
        colors = listOf(GreenGlow, CardBackground),
        radius = 800f,
        center = Offset(300f, 200f)
    )

    Box(
        modifier = modifier
            .clip(cardShape)
            .background(backgroundBrush)
            .padding(18.dp)
    ) {
        Column {
            // --- HEADER: Teks GOALS TRACKER ---
            Text(
                text = "GOALS TRACKER",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- TENGAH: Segmented Circular Progress ---
            Box(
                modifier = Modifier
                    .height(height ?: 200.dp)
                    .width(150.dp)
                    .aspectRatio(1f), // Buat jadi persegi sempurna
                contentAlignment = Alignment.Center
            ) {
                // Gambar Garis Lingkaran Terputus
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val numSegments = 40 // Total kotak/garis
                    val anglePerSegment = 360f / numSegments
                    val activeSegments = (numSegments * (percentage / 100f)).toInt()

                    val center = Offset(size.width / 2, size.height / 2)
                    val radius = size.minDimension / 2
                    val dashLength = 20.dp.toPx() // Panjang setiap kotak hijau
                    val dashThickness = 6.dp.toPx() // Ketebalan kotak hijau

                    // 1. Gambar Garis Tipis di bagian dalam
                    drawArc(
                        color = Color.White.copy(alpha = 0.5f),
                        startAngle = 140f,
                        sweepAngle = 260f, // Tidak penuh melingkar
                        useCenter = false,
                        style = Stroke(width = 1.dp.toPx()),
                        topLeft = Offset(24.dp.toPx(), 24.dp.toPx()), // Jarak ke dalam
                        size = Size(size.width - 48.dp.toPx(), size.height - 48.dp.toPx())
                    )

                    // 2. Gambar Segmen Kotak-kotak
                    for (i in 0 until numSegments) {
                        // Mulai dari -90 derajat agar dimulai dari puncak atas (jam 12)
                        val angle = (i * anglePerSegment) - 90f
                        val isActive = i < activeSegments
                        val segmentColor = if (isActive) ActiveGreen else InactiveGreen

                        // Kita gunakan fungsi putar (rotate) bawaan Canvas.
                        // Ini memutar sumbu kanvas, lalu kita cukup gambar garis lurus vertikal ke bawah
                        rotate(degrees = angle, pivot = center) {
                            drawLine(
                                color = segmentColor,
                                start = Offset(center.x, 0f), // Titik paling luar
                                end = Offset(center.x, dashLength), // Ditarik sedikit ke dalam
                                strokeWidth = dashThickness,
                                cap = StrokeCap.Butt // Ujungnya rata/kotak (bukan bulat)
                            )
                        }
                    }
                }

                // Teks Persentase di Tengah
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Placeholder untuk Ikon Koin (Bisa diganti dengan Image painterResource)
                    Box(modifier = Modifier.size(24.dp).background(Color(0xFFFFB300), RoundedCornerShape(50)))

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "$percentage%",
                        color = Color.White,
                        fontSize = fontSize ?: 42.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- FOOTER: Angka Total Saved ---
            Text(
                text = "TOTAL SAVED",
                color = TextGrey,
                fontSize = 10.sp,
                letterSpacing = 1.sp
            )

            // Menggunakan ilmu buildAnnotatedString yang kita bahas sebelumnya!
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                        append(currentSaved)
                    }
                    withStyle(style = SpanStyle(color = TextGrey, fontWeight = FontWeight.Medium)) {
                        append("/$targetSaved")
                    }
                },
                fontSize = 18.sp
            )
        }
    }
}