package cz.ackee.testtask.rm.core.presentation.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import cz.ackee.testtask.rm.core.presentation.navigation.model.NavButtonState
import cz.ackee.testtask.rm.core.presentation.utils.fromMainScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavViewModel(
    isBottomBarVisible: Boolean = true
) : ViewModel() {

    private val _navButtons = MutableStateFlow(NavButtonState.asBottomNavButtonList())
    val navButtons = _navButtons.asStateFlow()

    private val _isBottomBarVisible = MutableStateFlow(isBottomBarVisible)
    val isBottomBarVisible = _isBottomBarVisible.asStateFlow()

    private fun updateBottomBar(screen: MainScreens) {
        _navButtons.update { buttons ->
            buttons.map { button ->
                button.updateActive(isActive = button.screen == screen)
            }
        }
        _isBottomBarVisible.update {
            when (screen) {
                is MainScreens.Search, is MainScreens.CharacterDetail -> false
                else -> true
            }
        }
    }

    fun updateBottomBar(backStack: NavBackStackEntry?) {
        if (backStack == null) return
        val currScreen = backStack.fromMainScreen() ?: return

        updateBottomBar(screen = currScreen)
    }


    fun navigateToScreen(
        navController: NavController,
        screen: MainScreens
    ) {
        navController.navigate(screen) {
            launchSingleTop = true
        }
    }

}