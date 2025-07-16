package cz.ackee.testtask.rm.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens

@Composable
fun AppNavHost(
    screenPadding: PaddingValues = PaddingValues(),
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainScreens.Characters,
        contentAlignment = Alignment.Center
    ) {
        composable<MainScreens.Characters> {

        }
        composable<MainScreens.Search> {

        }
        composable<MainScreens.Favorites> {

        }
        composable<MainScreens.CharacterDetail> {

        }
    }
}