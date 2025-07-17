package cz.ackee.testtask.rm.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.ackee.testtask.rm.character.presentation.screen.CharactersScreenWrapper
import cz.ackee.testtask.rm.character.presentation.screen.FavouriteCharactersScreenWrapper
import cz.ackee.testtask.rm.character.presentation.screen.SearchCharactersScreenWrapper
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel

@Composable
fun AppNavHost(
    screenPadding: PaddingValues = PaddingValues(),
    navController: NavHostController,
    navViewModel: NavViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = MainScreens.Characters,
        contentAlignment = Alignment.Center
    ) {
        composable<MainScreens.Characters> {
            CharactersScreenWrapper(
                screenPadding = screenPadding,
                navController = navController,
                navViewModel = navViewModel
            )
        }
        composable<MainScreens.Search> {
            SearchCharactersScreenWrapper(
                screenPadding = screenPadding,
                navController = navController,
                navViewModel = navViewModel
            )
        }
        composable<MainScreens.Favorites> {
            FavouriteCharactersScreenWrapper(
                screenPadding = screenPadding,
                navController = navController,
                navViewModel = navViewModel
            )
        }
        composable<MainScreens.CharacterDetail> {

        }
    }
}