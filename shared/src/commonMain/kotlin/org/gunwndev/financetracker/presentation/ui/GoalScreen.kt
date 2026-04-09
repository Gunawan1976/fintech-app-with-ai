package org.gunwndev.financetracker.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gunwndev.financetracker.domain.entity.TransactionEntitty
import org.gunwndev.financetracker.presentation.state.FinanceState
import org.gunwndev.financetracker.presentation.ui.components.CircularProgressWithText
import org.gunwndev.financetracker.presentation.ui.theme.Dark1A
import org.gunwndev.financetracker.presentation.ui.theme.NeonGreen
import org.gunwndev.financetracker.presentation.utils.categories
import org.gunwndev.financetracker.presentation.utils.formatMillisToDateString
import org.gunwndev.financetracker.presentation.utils.formatWithCommas

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GoalScreen(
    state: FinanceState,
    onSave: (title: String, category: String, expiryDateMillis: Long, amount: Long, total_amount: Long) -> Unit,
    onDelete: (TransactionEntitty) -> Unit
) {
    val ActiveGreen = Color(0xFF39FF14) // Hijau neon terang
    val InactiveGreen = Color(0xFF253825) // Hijau sangat gelap

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var showSheet by remember { mutableStateOf(false) }

    // State untuk Form
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var expiryDateMillis by remember { mutableStateOf(0L) }
    var amount by remember { mutableStateOf(0L) }
    var total_amount by remember { mutableStateOf(0L) }

    val scrollState = rememberScrollState()

    val currentSaved = remember(state.items) {
        state.items.sumOf { it.amount }
    }

    val totalTarget = remember(state.items) {
        state.items.sumOf { it.totalAmount }
    }

// Di barisan state atas (dekat showDeleteDialog)
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedItemForDelete by remember { mutableStateOf<TransactionEntitty?>(null) }

// Menghitung persentase keseluruhan (untuk progress bar lingkaran)
    val totalPercentageValue = remember(currentSaved, totalTarget) {
        if (totalTarget > 0) (currentSaved.toFloat() / totalTarget.toFloat()) else 0f
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

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        containerColor = Dark1A,
    ) {
        Column(
            modifier = Modifier.padding(18.dp).verticalScroll(scrollState).statusBarsPadding()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(Dark1A),
                horizontalArrangement = Arrangement.SpaceBetween, // Dorong konten ke ujung kiri dan kanan
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GOALS",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black, // Font sangat tebal
                    letterSpacing = 1.sp
                )

                Box(
                    modifier = Modifier.clip(
                        RoundedCornerShape(
                            topStart = 32.dp, topEnd = 32.dp, bottomEnd = 32.dp, bottomStart = 32.dp
                        )
                    ) // Gunakan bentuk kustom yang kita buat
                        .background(NeonGreen).padding(10.dp)
                        .clickable(
                            onClick = {
                                showSheet = true
                            }
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Turun",
                            tint = Dark1A,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "SET A GOAL",
                            color = Dark1A,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Error: ${state.error}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                state.items.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Data masih kosong",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

                else -> {
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth().background(Dark1A),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {
                            Text(
                                text = "TOTAL SAVED",
                                color = Color(0xFFA0A0A0),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp
                            )

                            Text(
                                text = currentSaved.formatWithCommas(),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 70.sp
                            )

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color(0xFFA0A0A0),
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 22.sp
                                        )
                                    ) {
                                        append("/ ${totalTarget.formatWithCommas()}")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color(0xFFA0A0A0),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    ) {
                                        append("EGP")
                                    }
                                },
                            )

                        }
                        Spacer(modifier = Modifier.width(20.dp))

                        Box(
                            modifier = Modifier.height(150.dp)

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
                                Box(
                                    modifier = Modifier.size(24.dp)
                                        .background(Color(0xFFFFB300), RoundedCornerShape(50))
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${totalPercentageDisplay}%",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))

                    state.items.forEach { item ->
                        Surface(
                            color = Color(0xFF0D1110), // Background gelap
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .combinedClickable(
                                    onClick = {
                                        /* Klik biasa */
                                        println("Item diklik")
                                    },
                                    onLongClick = {
                                        selectedItemForDelete = item
                                        showDeleteDialog = true
                                    },
                                    onLongClickLabel = "Hapus Goal" // Opsi untuk Accessibility
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Sisi Kiri: Judul, Tag, dan Tanggal
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = item.name,
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        // Tag Category
                                        Surface(
                                            color = Color(0xFF1A2E2A),
                                            shape = RoundedCornerShape(50),
                                        ) {
                                            Text(
                                                text = item.category,
                                                modifier = Modifier.padding(
                                                    horizontal = 12.dp,
                                                    vertical = 4.dp
                                                ),
                                                style = TextStyle(
                                                    color = Color(0xFF4DB6AC),
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = formatMillisToDateString(item.expiryDateMillis),
                                            style = TextStyle(color = Color.Gray, fontSize = 12.sp)
                                        )
                                    }
                                }

                                // Sisi Kanan: Angka dan Progress
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(
                                        horizontalAlignment = Alignment.End,
                                        modifier = Modifier.padding(end = 12.dp)
                                    ) {
                                        Text(
                                            text = item.amount.formatWithCommas(),
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 18.sp,
                                                fontFamily = FontFamily.Monospace, // Gaya robotik/tech
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Text(
                                            text = "/${item.totalAmount.formatWithCommas()}",
                                            style = TextStyle(
                                                color = Color.Gray,
                                                fontSize = 14.sp,
                                                fontFamily = FontFamily.Monospace
                                            )
                                        )
                                    }
                                    CircularProgressWithText(progress = (item.amount.toFloat()/item.totalAmount.toFloat()))
//                                    CircularProgressWithText(progress = 0.6f)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(100.dp)) // Ruang untuk Bottom Nav
                }
            }

            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState,
                    containerColor = Color(0xFF121212), // Background gelap sesuai gambar
                    contentColor = Color.White,
                    dragHandle = { BottomSheetDefaults.DragHandle(color = Color.Gray) }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .navigationBarsPadding(), // Agar tidak tertutup navigasi sistem
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "Set a New Goal",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        // Input Nama Goal
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Goal Name (ex: Buy a camera)") },
                            textStyle = TextStyle(color = Color.White),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.DarkGray,
                                focusedBorderColor = Color(0xFF00FF5F) // Hijau neon sesuai gambar
                            )
                        )

                        OutlinedTextField(
                            value = amount.toString(),
                            onValueChange = { amount = it.toLongOrNull() ?: 0 },
                            textStyle = TextStyle(color = Color.White),
                            label = { Text("Amount (EGP)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.DarkGray,
                                focusedBorderColor = Color(0xFF00FF5F)
                            )
                        )

                        // Input Target Uang
                        OutlinedTextField(
                            value = total_amount.toString(),
                            onValueChange = { total_amount = it.toLongOrNull() ?: 0 },
                            textStyle = TextStyle(color = Color.White),
                            label = { Text("Target Amount (EGP)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.DarkGray,
                                focusedBorderColor = Color(0xFF00FF5F)
                            )
                        )

                        OutlinedTextField(
                            value = if (selectedDateMillis != null) {
                                formatMillisToDateString(selectedDateMillis!!)
                            } else {
                                ""
                            },
                            onValueChange = { },
                            label = { Text("Expiry Date", color = Color.White) },
                            placeholder = { Text("Pilih tanggal di kalender", color = Color.Gray) },
                            readOnly = true, // User tidak bisa ngetik, tapi tetap bisa diklik
                            enabled = true,  // Harus true supaya clickable-nya jalan
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showDatePicker = true }, // Klik di sini akan memicu dialog
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.DarkGray,
                                focusedBorderColor = Color(0xFF00FF5F),
                                disabledTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedTextColor = Color.White
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.clickable { showDatePicker = true } // Icon juga bisa diklik
                                )
                            }
                        )

                        // Pilihan Kategori (Simulasi Tag)
                        Text("Select Category", style = MaterialTheme.typography.bodyMedium)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            categories.forEach { cat ->
                                val isSelected = category == cat.name

                                FilterChip(
                                    selected = isSelected,
                                    onClick = { category = cat.name },
                                    label = { Text(cat.name, color = Color.White) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = cat.color.copy(alpha = 0.2f),
                                        selectedLabelColor = cat.color
                                    ),
                                )
                            }
                        }

                        // Tombol Simpan
                        Button(
                            onClick = {
                                onSave(title, category, selectedDateMillis!!, amount, total_amount)
                                showSheet = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FF5F)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                "SAVE GOAL",
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
    if(showDeleteDialog && selectedItemForDelete != null){
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                selectedItemForDelete = null
            },
            containerColor = Color(0xFF1A1A1A), // Background gelap sesuai tema
            title = {
                Text(
                    text = "Hapus Goal?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Apakah kamu yakin ingin menghapus '${selectedItemForDelete?.name}'? Tindakan ini tidak bisa dibatalkan.",
                    color = Color.Gray
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        selectedItemForDelete?.let { onDelete(it) }
                        showDeleteDialog = false
                        selectedItemForDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("HAPUS", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        selectedItemForDelete = null
                    }
                ) {
                    Text("BATAL", color = Color.White)
                }
            }
        )
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedDateMillis = datePickerState.selectedDateMillis
                        // Update juga state untuk form di sini!
                        expiryDateMillis = datePickerState.selectedDateMillis ?: 0L
                        showDatePicker = false
                    },
                    enabled = datePickerState.selectedDateMillis != null
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Batal")
                }
            }
        ) {
            // Komponen Kalender Material 3
            DatePicker(state = datePickerState)
        }
    }
}