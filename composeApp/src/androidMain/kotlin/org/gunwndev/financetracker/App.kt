package org.gunwndev.financetracker

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview
import org.gunwndev.financetracker.presentation.ui.HomeScreen
import org.gunwndev.financetracker.presentation.ui.theme.FinanceTrackerTheme

import org.gunwndev.financetracker.presentation.ui.SplashScreen
import org.gunwndev.financetracker.presentation.viewmodel.FinanceViewModel
import org.koin.compose.viewmodel.koinViewModel


enum class BottomNavRoute(
    val title: String,
    val icon: @Composable (tint: Color) -> Unit
) {
    Home(
        title = "Home",
        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = tint) }
    ),
    Inventory(
        title = "Inventory",
        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "Inventory", tint = tint) }
    ),
    Resep(
        title = "Resep",
        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "Profile", tint = tint) }
    ),
    Profile(
        title = "Profile",
        icon = { tint -> Icon(imageVector = Icons.Default.Home, contentDescription = "Profile", tint = tint) }
    )
}

enum class FullScreenRoute { Splash, Main }

@Composable
@Preview
fun App() {
    FinanceTrackerTheme {
        val viewModel = koinViewModel<FinanceViewModel>()
        val state by viewModel.state.collectAsState()

        var currentScreen by remember { mutableStateOf(FullScreenRoute.Splash) }
        var currentBottomTab by remember { mutableStateOf(BottomNavRoute.Home) }

        when (currentScreen) {
            FullScreenRoute.Splash -> SplashScreen(
                onSwipeComplete = {
                    currentScreen = FullScreenRoute.Main
                }
            )
            FullScreenRoute.Main -> HomeScreen(

            )
        }
    }
}
