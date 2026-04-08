package org.gunwndev.financetracker.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.gunwndev.financetracker.presentation.event.FinanceEvent
import org.gunwndev.financetracker.presentation.state.FinanceState
import org.gunwndev.financetracker.presentation.ui.components.CustomBottomNav
import org.gunwndev.financetracker.presentation.utils.Screens
import org.gunwndev.financetracker.presentation.viewmodel.FinanceViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun MainDashboardScreen(state: FinanceState,viewModel: FinanceViewModel) {
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
                    state =state,
                    onAddClick = {
                        viewModel.onEvent(
                            FinanceEvent.SaveItem(
                                name = "Item Baru",
                                category = "Kategori Baru",
                                expiryDateMillis = 0,
                                amount = 100,
                                total_amount = 200
                            )
                        )
                    }
                )
            }

            composable(Screens.Goals.route) {
                GoalScreen(
                    state =state,
                    onSave = { name, category, expiryDateMillis,amount,total_amount ->
                        viewModel.onEvent(
                            FinanceEvent.SaveItem(
                                name = name,
                                category = category,
                                expiryDateMillis = expiryDateMillis,
                                amount = amount,
                                total_amount = total_amount
                            )
                        )
                    },
                    onDelete = { item ->
                        viewModel.onEvent(FinanceEvent.DeleteItem(item))
                    }
                )
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
            state = FinanceState(),
            viewModel = viewModel()
        )
    }
}