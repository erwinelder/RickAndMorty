package cz.ackee.testtask.rm.core.presentation.component.screenContainer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.ackee.testtask.rm.core.presentation.navigation.component.BottomNavBar
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScaffold(
    navViewModel: NavViewModel = koinViewModel(),
    content: @Composable (PaddingValues) -> Unit
) {
    val navButtons by navViewModel.navButtons.collectAsStateWithLifecycle()
    val isBottomBarVisible by navViewModel.isBottomBarVisible.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navButtons = navButtons,
                isVisible = isBottomBarVisible
            )
        },
        containerColor = AppColors.background,
        modifier = Modifier.fillMaxSize()
    ) { screenPadding ->
        content(screenPadding)
    }
}