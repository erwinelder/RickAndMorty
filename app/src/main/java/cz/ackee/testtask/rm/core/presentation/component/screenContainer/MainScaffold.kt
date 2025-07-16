package cz.ackee.testtask.rm.core.presentation.component.screenContainer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import cz.ackee.testtask.rm.core.presentation.navigation.AppNavHost
import cz.ackee.testtask.rm.core.presentation.navigation.component.BottomNavBar
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val navViewModel = koinViewModel<NavViewModel>()

    val navButtons by navViewModel.navButtons.collectAsStateWithLifecycle()
    val isBottomBarVisible by navViewModel.isBottomBarVisible.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navButtons = navButtons,
                isVisible = isBottomBarVisible
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { screenPadding ->
        AppNavHost(
            screenPadding = screenPadding,
            navController = navController,
        )
    }
}