package cz.ackee.testtask.rm.core.presentation.component.screenContainer

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.ackee.testtask.rm.core.presentation.navigation.component.BottomNavBar
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import cz.ackee.testtask.rm.core.presentation.utils.bottom
import cz.ackee.testtask.rm.core.presentation.utils.copy
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScaffold(
    navController: NavController = rememberNavController(),
    navViewModel: NavViewModel = koinViewModel(),
    content: @Composable (PaddingValues) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navButtons by navViewModel.navButtons.collectAsStateWithLifecycle()
    val isBottomBarVisible by navViewModel.isBottomBarVisible.collectAsStateWithLifecycle()

    val bottomSystemBarPadding = WindowInsets.systemBars.asPaddingValues().bottom

    LaunchedEffect(navBackStackEntry) {
        navViewModel.updateBottomBar(backStack = navBackStackEntry)
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navButtons = navButtons,
                isVisible = isBottomBarVisible,
                bottomPadding = bottomSystemBarPadding,
                onClick = {
                    navViewModel.navigateToScreen(
                        navController = navController,
                        screen = it
                    )
                }
            )
        },
        containerColor = AppColors.background,
        modifier = Modifier.fillMaxSize()
    ) { screenPadding ->
        val bottomPadding by animateDpAsState(
            (screenPadding.bottom - bottomSystemBarPadding).coerceAtLeast(0.dp)
        )
        val padding = remember(screenPadding, bottomPadding) {
            screenPadding.copy(bottom = bottomPadding)
        }

        content(padding)
    }
}