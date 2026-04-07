package org.gunwndev.financetracker.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import financetrackerwithai.shared.generated.resources.Res
import financetrackerwithai.shared.generated.resources.emoji
import financetrackerwithai.shared.generated.resources.profit
import financetrackerwithai.shared.generated.resources.target
import financetrackerwithai.shared.generated.resources.trophy
import org.jetbrains.compose.resources.painterResource


enum class FullScreenRoute { Splash, Main, Goal }

//enum class RouteId{
//    SAVINGS,
//    GOALS,
//    REWARDS,
//    EMOJI
//}

enum class BottomNavRoute(
    val title: String,
    val icon: @Composable (tint: Color) -> Unit
) {
    Home(
        title = "SAVINGS",
        icon = { tint ->
            Icon(
                painterResource(Res.drawable.profit),
                contentDescription = "SAVINGS",
                tint = tint
            )
        }
    ),
    Inventory(
        title = "GOALS",
        icon = { tint ->
            Icon(
                painterResource(Res.drawable.target),
                contentDescription = "GOALS",
                tint = tint
            )
        }
    ),
    Resep(
        title = "REWARDS",
        icon = { tint ->
            Icon(
                painterResource(Res.drawable.trophy),
                contentDescription = "REWARDS",
                tint = tint
            )
        }
    ),
    Profile(
        title = "EMOJI",
        icon = { tint ->
            Icon(
                painterResource(Res.drawable.emoji),
                contentDescription = "EMOJI",
                tint = tint
            )
        }
    )
}
