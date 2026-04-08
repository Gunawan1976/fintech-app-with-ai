package org.gunwndev.financetracker.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessBottomSheet(
    onDismiss: () -> Unit,
    onSetAnotherGoal: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xFF32CD32), // Warna Hijau Neon
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        dragHandle = {
            // Garis hitam kecil di atas
            Box(
                Modifier
                    .padding(vertical = 12.dp)
                    .width(40.dp)
                    .height(4.dp)
                    .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(50))
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp) // Beri ruang untuk navigation bar
        ) {
            // Background Grid (Opsional - Menggunakan Canvas)
            // Kamu bisa pakai asset gambar grid atau gambar manual dengan Canvas

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Icon Pixel Art (Gunakan Image Asset kamu)
//                Image(
//                    painter = painterResource(Res.drawable.pixel_arrow_up), // Ganti dengan assetmu
//                    contentDescription = null,
//                    modifier = Modifier.size(150.dp)
//                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 2. Banner "Save. Flex. Repeat." (Rotated Box)
                Box(
                    modifier = Modifier
                        .graphicsLayer(rotationZ = -2f) // Sedikit miring ala Neo-Brutalism
                        .background(Color.Black)
                        .padding(vertical = 4.dp, horizontal = 12.dp)
                ) {
                    Text(
                        text = "Save. Flex. Repeat. 💸🔥",
                        color = Color.White,
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 3. Teks "YOU DID IT!" dengan Shadow Tebal
                Box {
                    // Shadow effect manual
                    Text(
                        text = "YOU DID IT!",
                        style = TextStyle(
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black, // Warna bayangan
                        ),
                        modifier = Modifier.offset(x = 4.dp, y = 4.dp)
                    )
                    Text(
                        text = "YOU DID IT!",
                        style = TextStyle(
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White, // Warna utama
                        )
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 4. Tombol "SET ANOTHER GOAL?" (Neo-Brutalism Style)
                Button(
                    onClick = onSetAnotherGoal,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
//                        .drawBehind {
//                            // Border/Shadow kaku di bawah tombol
//                            drawRoundRect(
//                                color = Color.Black,
//                                topLeft = Offset(0f, 0f),
//                                size = size,
//                                style = Stroke(width = 4.dp.toPx())
//                            )
//                        },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(
                        text = "SET ANOTHER GOAL?",
                        color = Color.Black,
                        style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                    )
                }
            }

            // Tombol Close (X) di pojok kanan atas
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .background(Color.Black.copy(alpha = 0.2f), CircleShape)
            ) {
                Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
            }
        }
    }
}