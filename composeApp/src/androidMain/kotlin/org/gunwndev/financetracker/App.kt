package org.gunwndev.financetracker

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import org.gunwndev.financetracker.presentation.ui.MainDashboardScreen
import org.gunwndev.financetracker.presentation.ui.theme.FinanceTrackerTheme

import org.gunwndev.financetracker.presentation.ui.SplashScreen
import org.gunwndev.financetracker.presentation.viewmodel.FinanceViewModel
import org.koin.compose.viewmodel.koinViewModel



@Composable
@Preview
fun App() {
    FinanceTrackerTheme {
        val viewModel = koinViewModel<FinanceViewModel>()
        val state by viewModel.state.collectAsState()

        var showMainDashboard by remember { mutableStateOf(false) }

        if (!showMainDashboard) {
            SplashScreen(onSwipeComplete = { showMainDashboard = true })
        } else {
            // Kamu bisa kirim state/viewmodel ke MainDashboard jika diperlukan
            MainDashboardScreen(state = state)
        }
    }
}
