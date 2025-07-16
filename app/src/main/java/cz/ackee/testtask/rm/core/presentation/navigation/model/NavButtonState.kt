package cz.ackee.testtask.rm.core.presentation.navigation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import cz.ackee.testtask.rm.R

sealed class NavButtonState(
    val screen: MainScreens,
    @StringRes val nameRes: Int,
    @DrawableRes val iconRes: Int,
    open val isActive: Boolean
) {

    data class Characters(
        override val isActive: Boolean = false
    ) : NavButtonState(
        screen = MainScreens.Characters,
        nameRes = R.string.characters,
        iconRes = R.drawable.characters,
        isActive = isActive
    )

    data class Favorites(
        override val isActive: Boolean = false
    ) : NavButtonState(
        screen = MainScreens.Favorites,
        nameRes = R.string.favorites,
        iconRes = R.drawable.favorites,
        isActive = isActive
    )


    fun updateActive(isActive: Boolean): NavButtonState {
        return when (this) {
            is Characters -> this.copy(isActive = isActive)
            is Favorites -> this.copy(isActive = isActive)
        }
    }


    companion object {

        fun asBottomNavButtonList(): List<NavButtonState> {
            return listOf(
                Characters(isActive = true),
                Favorites(isActive = false),
            )
        }

    }

}
