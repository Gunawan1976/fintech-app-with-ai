package org.gunwndev.financetracker.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import financetrackerwithai.shared.generated.resources.Res
import financetrackerwithai.shared.generated.resources.hand
import org.gunwndev.financetracker.presentation.ui.theme.CyberYellow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeItemComponents(color: Color, icon: DrawableResource, text: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(85.dp)
                .clip(CircleShape)
                .background(color)
                .border(
                    2.dp,
                    Color.White,
                    CircleShape
                ), // Garis putih di sekeliling profil
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = text,
                modifier = Modifier.size(55.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
        )
    }
}