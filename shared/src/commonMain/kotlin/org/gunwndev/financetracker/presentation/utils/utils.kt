package org.gunwndev.financetracker.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import financetrackerwithai.shared.generated.resources.Res
import financetrackerwithai.shared.generated.resources.emoji
import financetrackerwithai.shared.generated.resources.profit
import financetrackerwithai.shared.generated.resources.target
import financetrackerwithai.shared.generated.resources.trophy
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

sealed class Screens(val route: String, val icon: DrawableResource, val label: String) {
    object Savings : Screens("SAVINGS", Res.drawable.profit, "SAVINGS")
    object Goals : Screens("GOALS", Res.drawable.target, "GOALS")

    object Rewards : Screens("REWARDS", Res.drawable.trophy, "REWARDS")

    object Emoji : Screens("EMOJI", Res.drawable.emoji, "")

}

data class GoalCategory(val name: String, val color: Color)

val categories = listOf(
    GoalCategory("Tech & gear", Color(0xFF1D3535)),
    GoalCategory("Life style", Color(0xFF2C2235)),
    GoalCategory("Travel", Color(0xFF352E1D)),
    GoalCategory("Others", Color(0xFF352E1D))
)

fun Long.formatWithCommas(): String {
    return this.toString().reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}