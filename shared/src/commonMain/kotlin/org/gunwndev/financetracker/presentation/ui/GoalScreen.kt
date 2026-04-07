package org.gunwndev.financetracker.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
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
import financetrackerwithai.shared.generated.resources.notification
import org.gunwndev.financetracker.presentation.state.FinanceState
import org.gunwndev.financetracker.presentation.ui.theme.CyberYellow
import org.gunwndev.financetracker.presentation.ui.theme.Dark1A
import org.gunwndev.financetracker.presentation.ui.theme.MagentaPink
import org.gunwndev.financetracker.presentation.ui.theme.NeonGreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GoalScreen() {
    Scaffold(
        containerColor = Dark1A,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Dark1A)
                // Padding aman untuk status bar (atas) dan jarak sisi (kiri-kanan)
                .padding(horizontal = 20.dp, vertical = 16.dp),
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
                modifier = Modifier
                    .clip(RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp,
                        bottomEnd = 32.dp,
                        bottomStart = 32.dp
                    )) // Gunakan bentuk kustom yang kita buat
                    .background(NeonGreen)
                    .padding(10.dp)
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
    }
}

@Preview
@Composable
fun GoalScreenPreview() {
    MaterialTheme {
        GoalScreen(

        )
    }
}