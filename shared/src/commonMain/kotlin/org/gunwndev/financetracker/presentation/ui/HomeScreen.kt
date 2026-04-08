package org.gunwndev.financetracker.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import financetrackerwithai.shared.generated.resources.Res
import financetrackerwithai.shared.generated.resources.gift
import financetrackerwithai.shared.generated.resources.hand
import financetrackerwithai.shared.generated.resources.lightning
import financetrackerwithai.shared.generated.resources.naughty
import financetrackerwithai.shared.generated.resources.notification
import org.gunwndev.financetracker.presentation.state.FinanceState
import org.gunwndev.financetracker.presentation.ui.components.GoalsTrackerCard
import org.gunwndev.financetracker.presentation.ui.components.HomeItemComponents
import org.gunwndev.financetracker.presentation.ui.components.SpendeesCard
import org.gunwndev.financetracker.presentation.ui.theme.CyberYellow
import org.gunwndev.financetracker.presentation.ui.theme.Dark1A
import org.gunwndev.financetracker.presentation.ui.theme.DeepPurple
import org.gunwndev.financetracker.presentation.ui.theme.ElectricCyan
import org.gunwndev.financetracker.presentation.ui.theme.MagentaPink
import org.gunwndev.financetracker.presentation.ui.theme.NeonGreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( state: FinanceState, onAddClick: () -> Unit) {
    val WidgetShape = RoundedCornerShape(
        topStart = 32.dp,
        topEnd = 8.dp,
        bottomEnd = 32.dp,
        bottomStart = 8.dp
    )
    val scrollState = rememberScrollState()

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
                            .border(2.dp, Color.White, CircleShape), // Garis putih di sekeliling profil
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
            modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp).verticalScroll(scrollState).safeDrawingPadding(),
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
                                text = "15,750",
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
                )

                HomeItemComponents(
                    text = "Log Spend",
                    icon = Res.drawable.naughty,
                    color = DeepPurple,
                )

                HomeItemComponents(
                    text = "Rewards",
                    icon = Res.drawable.gift,
                    color = MagentaPink
                )

                HomeItemComponents(
                    text = "Challenge",
                    icon = Res.drawable.lightning,
                    color = ElectricCyan
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                GoalsTrackerCard(height = 120.dp, fontSize = 24.sp)
                SpendeesCard(height = 120.dp)
            }

            Spacer(modifier = Modifier.height(24.dp))


//            when {
//                state.isLoading -> {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        CircularProgressIndicator()
//                    }
//                }
//
//                state.error != null -> {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        Text(
//                            text = "Error: ${state.error}",
//                            color = MaterialTheme.colorScheme.error
//                        )
//                    }
//                }
//
//                state.items.isEmpty() -> {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        Text(
//                            text = "Pantry masih kosong. Yuk tambah barang!",
//                            style = MaterialTheme.typography.bodyLarge,
//                            color = Color.White,
//                            modifier = Modifier.padding(horizontal = 16.dp)
//                        )
//                    }
//                }
//
//                else -> {
//                    Spacer(modifier = Modifier.height(24.dp))
//
//                    state.items.forEach { item ->
//                        Box(
//                            modifier = Modifier.padding(top = 8.dp, start = 12.dp, end = 12.dp)
//                        ) {
//                            Text(item.name, color = Color.White)
//                        }
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//
//                    Spacer(modifier = Modifier.height(100.dp)) // Ruang untuk Bottom Nav
//                }
//            }

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            state = FinanceState(),
            onAddClick = {}

        )
    }
}
