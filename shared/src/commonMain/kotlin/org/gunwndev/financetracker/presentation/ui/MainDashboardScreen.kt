package org.gunwndev.financetracker.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.gunwndev.financetracker.presentation.state.FinanceState
import org.gunwndev.financetracker.presentation.ui.components.CustomBottomNav
import org.gunwndev.financetracker.presentation.utils.Screens
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainDashboardScreen(state: FinanceState) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Gunakan Box agar kita bisa menumpuk BottomNav di atas NavHost secara manual
    Box(modifier = Modifier.fillMaxSize()) {

        // 1. Konten Utama (NavHost)
        NavHost(
            navController = navController,
            startDestination = Screens.Savings.route,
            modifier = Modifier.fillMaxSize() // Isi seluruh layar
        ) {
            composable(Screens.Savings.route) {
                HomeScreen(
                    state = FinanceState(),
                    onAddClick = {}
                )
            }

            composable(Screens.Goals.route) {
                GoalScreen()
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Letakkan di paling bawah tengah
                .navigationBarsPadding() // Tambahkan padding navigasi sistem Android
                .fillMaxWidth()
        ) {
            CustomBottomNav(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MainDashboardScreenPreview() {
    MaterialTheme {
        MainDashboardScreen(
            state = FinanceState()
        )
    }
}