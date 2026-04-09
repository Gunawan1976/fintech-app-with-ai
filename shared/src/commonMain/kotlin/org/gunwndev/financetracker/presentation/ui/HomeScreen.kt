package org.gunwndev.financetracker.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import financetrackerwithai.shared.generated.resources.Res
import financetrackerwithai.shared.generated.resources.gift
import financetrackerwithai.shared.generated.resources.hand
import financetrackerwithai.shared.generated.resources.lightning
import financetrackerwithai.shared.generated.resources.naughty
import financetrackerwithai.shared.generated.resources.notification
import org.gunwndev.financetracker.presentation.state.FinanceState
import org.gunwndev.financetracker.presentation.ui.components.GlowingBubble
import org.gunwndev.financetracker.presentation.ui.components.HomeItemComponents
import org.gunwndev.financetracker.presentation.ui.theme.CyberYellow
import org.gunwndev.financetracker.presentation.ui.theme.Dark1A
import org.gunwndev.financetracker.presentation.ui.theme.DeepPurple
import org.gunwndev.financetracker.presentation.ui.theme.ElectricCyan
import org.gunwndev.financetracker.presentation.ui.theme.MagentaPink
import org.gunwndev.financetracker.presentation.ui.theme.NeonGreen
import org.gunwndev.financetracker.presentation.utils.formatWithCommas
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: FinanceState, onClick: () -> Unit, onLogSpendClick: () -> Unit, // Ini untuk Log Spend
    onRewardsClick: () -> Unit,  // Ini untuk Rewards
    onChallengeClick: () -> Unit
) {
    val WidgetShape = RoundedCornerShape(
        topStart = 32.dp,
        topEnd = 8.dp,
        bottomEnd = 32.dp,
        bottomStart = 8.dp
    )

    val CardBackground = Color(0xFF141915)
    val GreenGlow = Color(0xFF1E3A21)
    val ActiveGreen = Color(0xFF39FF14) // Hijau neon terang
    val InactiveGreen = Color(0xFF253825) // Hijau sangat gelap
    val TextGrey = Color(0xFFA0A0A0)
    val CardBg = Color(0xFF141416) // Latar hitam/abu sangat gelap

    // Warna Gelembung (Pusat Terang -> Tepi Gelap)
    val PurpleLight = Color(0xFF8B5CF6)
    val PurpleDark = Color(0xFF4C1D95)

    val CyanLight = Color(0xFF22D3EE)
    val CyanDark = Color(0xFF0891B2)

    val PinkLight = Color(0xFFF472B6)
    val PinkDark = Color(0xFFBE185D)

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

    val scrollState = rememberScrollState()

    val currentSaved = remember(state.items) {
        state.items.sumOf { it.amount }
    }

    val totalSaved = remember(state.items) {
        state.items.sumOf { it.totalAmount }
    }

    // Menghitung persentase keseluruhan (untuk progress bar lingkaran)
    val totalPercentageValue = remember(currentSaved, totalSaved) {
        if (totalSaved > 0) (currentSaved.toFloat() / totalSaved.toFloat()) else 0f
    }

// Buat string format manual untuk tampilan Text agar tidak error 'format'
    val totalPercentageDisplay = remember(totalPercentageValue) {
        val percent = totalPercentageValue * 100
        if (percent % 1 == 0f) {
            "${percent.toInt()}" // Contoh: "50"
        } else {
            // Pembulatan manual 2 digit untuk KMP
            ((percent * 100).toInt() / 100.0).toString() // Contoh: "50.25"
        }
    }

    Scaffold(
        containerColor = Dark1A,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Dark1A)
                    // Padding aman untuk status bar (atas) dan jarak sisi (kiri-kanan)
                    .padding(horizontal = 20.dp, vertical = 16.dp).statusBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween, // Dorong konten ke ujung kiri dan kanan
                verticalAlignment = Alignment.CenterVertically
            ) {

                // --- BAGIAN KIRI: Logo & Merek ---
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    // Ikon Wajah Kiri (Ganti dengan asetmu)
//                    Image(
//                        painter = painterResource(Res.drawable.ic_pixel_smiley),
//                        contentDescription = "Logo Fundie",
//                        modifier = Modifier.size(32.dp)
//                    )
//
//                    // Placeholder jika belum ada gambar:
//                    Box(modifier = Modifier.size(32.dp).background(Color.White, CircleShape))
//
//                    Spacer(modifier = Modifier.width(12.dp))
//
//                    Text(
//                        text = "FUNANCE",
//                        color = Color.White,
//                        fontSize = 28.sp,
//                        fontWeight = FontWeight.Black, // Font sangat tebal
//                        letterSpacing = 1.sp
//                    )
//                }
                Text(
                    text = "FUNANCE",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black, // Font sangat tebal
                    letterSpacing = 1.sp
                )

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    // 1. Lingkaran Profil Kuning
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(CyberYellow)
                            .border(
                                2.dp,
                                Color.White,
                                CircleShape
                            ), // Garis putih di sekeliling profil
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.notification),
                            contentDescription = "notif",
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // 2. Badge Notifikasi Merah Muda
                    if (2 > 0) {
                        Box(
                            modifier = Modifier
                                // Posisikan di kiri atas profil
                                .align(Alignment.TopStart)
                                // Geser sedikit keluar dari lingkaran profil
                                .offset(x = (-6).dp, y = (-4).dp)
                                .size(22.dp)
                                .background(MagentaPink, CircleShape)
                                // Garis hitam tipis agar terpisah dari layar belakang
                                .border(1.dp, Color.Black, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = 2.toString(),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
//        floatingActionButton = {
//            FloatingActionButton(onClick = onAddClick) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
//            }
//        }

    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp)
                .verticalScroll(scrollState).safeDrawingPadding(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(WidgetShape) // Gunakan bentuk kustom yang kita buat
                    .background(NeonGreen)
                    .padding(22.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // --- Kolom Kiri: Teks ---
                    Column {
                        Text(
                            text = "TOTAL SAVINGS",
                            color = Dark1A,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Row untuk Angka Utama dan Mata Uang
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = currentSaved.formatWithCommas(),
                                color = Dark1A,
                                fontSize = 40.sp, // Angka besar
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "EGP",
                                color = Dark1A.copy(alpha = 0.7f),
                                fontSize = 20.sp, // Teks mata uang lebih kecil
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 6.dp) // Sesuaikan letaknya
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Row untuk bagian "Spent Today"
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                // Opsional: Tambahkan background sedikit gelap seperti di gambar
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Black.copy(alpha = 0.1f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Turun",
                                tint = Dark1A,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "1,376 EGP spent today",
                                color = Dark1A,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Image(
                        painter = painterResource(Res.drawable.lightning),
                        contentDescription = "petir",
                        modifier = Modifier
                            .size(100.dp)
                            .offset(x = 10.dp) // Sedikit digeser keluar agar efeknya mirip gambar
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeItemComponents(
                    text = "Set A Goal",
                    icon = Res.drawable.hand,
                    color = CyberYellow,
                    onClick = onClick
                )

                HomeItemComponents(
                    text = "Log Spend",
                    icon = Res.drawable.naughty,
                    color = DeepPurple,
                    onClick = {

                    }
                )

                HomeItemComponents(
                    text = "Rewards",
                    icon = Res.drawable.gift,
                    color = MagentaPink,
                    onClick = {

                    }
                )

                HomeItemComponents(
                    text = "Challenge",
                    icon = Res.drawable.lightning,
                    color = ElectricCyan,
                    onClick = {

                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Box(
                    modifier = Modifier
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
                                .height(120.dp)
                                .width(150.dp)
                                .aspectRatio(1f), // Buat jadi persegi sempurna
                            contentAlignment = Alignment.Center
                        ) {
                            // Gambar Garis Lingkaran Terputus
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val numSegments = 40 // Total kotak/garis
                                val anglePerSegment = 360f / numSegments
                                val activeSegments = (numSegments * totalPercentageValue).toInt()

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
                                    size = Size(
                                        size.width - 48.dp.toPx(),
                                        size.height - 48.dp.toPx()
                                    )
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
                                            end = Offset(
                                                center.x,
                                                dashLength
                                            ), // Ditarik sedikit ke dalam
                                            strokeWidth = dashThickness,
                                            cap = StrokeCap.Butt // Ujungnya rata/kotak (bukan bulat)
                                        )
                                    }
                                }
                            }

                            // Teks Persentase di Tengah
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                // Placeholder untuk Ikon Koin (Bisa diganti dengan Image painterResource)
                                Box(
                                    modifier = Modifier.size(24.dp)
                                        .background(Color(0xFFFFB300), RoundedCornerShape(50))
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "$totalPercentageDisplay%",
                                    color = Color.White,
                                    fontSize = 14.sp,
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
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append(currentSaved.formatWithCommas())
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = TextGrey,
                                        fontWeight = FontWeight.Medium
                                    )
                                ) {
                                    append("/${totalSaved.formatWithCommas()}")
                                }
                            },
                            fontSize = 18.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(cardShape)
                        .background(CardBg)
                        .padding(18.dp)
                ) {
                    Column {
                        // --- HEADER ---
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Placeholder Logo Kecil (Bisa ganti pakai Image)
                            Box(
                                modifier = Modifier.size(16.dp)
                                    .background(Color.Green, RoundedCornerShape(4.dp))
                            )
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
                                .height(120.dp)
                                .width(150.dp)
                                .aspectRatio(1f), // Buat jadi persegi sempurna,
                            contentAlignment = Alignment.Center
                        ) {
                            when {
                                state.isLoading -> {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }

                                state.error != null -> {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Error: ${state.error}",
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }

                                state.items.isEmpty() -> {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Data masih kosong",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 16.dp)
                                        )
                                    }
                                }

                                else -> {
                                    // Ambil 3 item pertama dari state
                                    val topThreeItems = remember(state.items) {
                                        state.items.sortedByDescending { it.amount }.take(3)
                                    }
                                    // Bungkus dalam Box agar bisa menggunakan offset secara bebas (tumpang tindih)
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(280.dp), // Beri ruang yang cukup untuk gelembung yang bergeser
                                        contentAlignment = Alignment.Center
                                    ) {
                                        topThreeItems.forEachIndexed { index, item ->
                                            val progress = if (item.totalAmount > 0)
                                                (item.amount.toFloat() / item.totalAmount.toFloat() * 100).toInt()
                                            else 0

                                            when (index) {
                                                0 -> { // GELEMBUNG UTAMA (Gede, Tengah)
                                                    GlowingBubble(
                                                        size = 140.dp,
                                                        colorStart = PurpleLight, // Bisa diganti warna dinamis nanti
                                                        colorEnd = PurpleDark,
                                                        percentage = "$progress%",
                                                        label = item.name.uppercase(),
                                                        modifier = Modifier.offset(y = (-20).dp)
                                                    )
                                                }

                                                1 -> { // GELEMBUNG KEDUA (Sedang, Kanan Bawah)
                                                    GlowingBubble(
                                                        size = 100.dp,
                                                        colorStart = CyanLight,
                                                        colorEnd = CyanDark,
                                                        percentage = "$progress%",
                                                        label = item.name.uppercase(),
                                                        modifier = Modifier.offset(
                                                            x = 80.dp,
                                                            y = 60.dp
                                                        )
                                                    )
                                                }

                                                2 -> { // GELEMBUNG KETIGA (Kecil, Kiri Bawah)
                                                    GlowingBubble(
                                                        size = 70.dp,
                                                        colorStart = PinkLight,
                                                        colorEnd = PinkDark,
                                                        percentage = "$progress%",
                                                        label = item.name.uppercase(),
                                                        modifier = Modifier.offset(
                                                            x = (-90).dp,
                                                            y = 50.dp
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    // Lanjutkan dengan list item atau konten lainnya di bawah Box...
                                }
                            }

//                            // Gelembung 1: ESSENTIALS (Paling Besar, di belakang/tengah)
//                            GlowingBubble(
//                                size = 140.dp,
//                                colorStart = PurpleLight,
//                                colorEnd = PurpleDark,
//                                percentage = "57%",
//                                label = "ESSENTIALS",
//                                modifier = Modifier.offset(y = (-20).dp)
//                            )
//
//                            // Gelembung 2: LIFESTYLE (Sedang, di Kanan Bawah)
//                            GlowingBubble(
//                                size = 100.dp,
//                                colorStart = CyanLight,
//                                colorEnd = CyanDark,
//                                percentage = "28%",
//                                label = "LIFESTYLE",
//                                modifier = Modifier.offset(x = 50.dp, y = 50.dp) // Geser ke kanan bawah
//                            )
//
//                            // Gelembung 3: IMPULSE (Kecil, di Kiri Bawah)
//                            GlowingBubble(
//                                size = 70.dp,
//                                colorStart = PinkLight,
//                                colorEnd = PinkDark,
//                                percentage = "15%",
//                                label = "IMPULSE",
//                                modifier = Modifier.offset(x = (-60).dp, y = 40.dp) // Geser ke kiri bawah
//                            )
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
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            state = FinanceState(),
            onClick = {},
            onLogSpendClick = {},
            onRewardsClick = {},
            onChallengeClick = {}

        )
    }
}
