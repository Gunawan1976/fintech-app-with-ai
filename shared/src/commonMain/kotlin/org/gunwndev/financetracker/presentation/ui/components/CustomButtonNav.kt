package org.gunwndev.financetracker.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.gunwndev.financetracker.presentation.utils.Screens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomBottomNav(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    // Container utama (Kapsul hitam transparan)
    Surface(
        color = Color(0xFF0D1110),
        shape = RoundedCornerShape(50), // Membuat bentuk kapsul
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp) // Jarak dari pinggir layar
            .height(72.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val items = listOf(Screens.Savings, Screens.Goals, Screens.Rewards, Screens.Emoji)

            items.forEach { screen ->
                val isSelected = currentRoute == screen.route

                // Item Navigasi
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .weight(if (isSelected) 1.5f else 1f) // Item terpilih lebih lebar
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) Color(0xFF66FF66) else Color.Transparent)
                        .clickable { onNavigate(screen.route) },
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(screen.icon),
                            modifier = Modifier.size(30.dp),
                            contentDescription = null,
                            tint = if (isSelected) Color.Black else Color.White
                        )
                        if (isSelected && screen.label.isNotEmpty()) {
                            Text(
                                text = screen.label,
                                modifier = Modifier.padding(start = 8.dp),
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}