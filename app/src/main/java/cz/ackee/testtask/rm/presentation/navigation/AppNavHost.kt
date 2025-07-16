package cz.ackee.testtask.rm.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.ackee.testtask.rm.presentation.navigation.model.MainScreens

@Composable
fun AppNavHost(
    screenPadding: PaddingValues = PaddingValues(),
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreens.Characters,
        contentAlignment = Alignment.Center
    ) {
        composable<MainScreens.Characters> {

        }
        composable<MainScreens.Search> {

        }
        composable<MainScreens.FavoriteCharacters> {

        }
        composable<MainScreens.CharacterDetail> {

        }
    }
}