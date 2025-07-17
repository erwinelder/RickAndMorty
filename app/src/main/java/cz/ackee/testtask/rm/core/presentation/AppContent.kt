package cz.ackee.testtask.rm.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.MainScaffold
import cz.ackee.testtask.rm.core.presentation.navigation.AppNavHost
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppContent() {
    val navController = rememberNavController()
    val navViewModel = koinViewModel<NavViewModel>()

    MainScaffold(
        navController = navController,
        navViewModel = navViewModel
    ) { screenPadding ->
        AppNavHost(
            screenPadding = screenPadding,
            navController = navController,
            navViewModel = navViewModel
        )
    }
}