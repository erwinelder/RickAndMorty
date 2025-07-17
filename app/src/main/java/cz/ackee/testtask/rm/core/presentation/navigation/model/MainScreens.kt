package cz.ackee.testtask.rm.core.presentation.navigation.model

import kotlinx.serialization.Serializable

sealed interface MainScreens {

    @Serializable
    data object Characters : MainScreens

    @Serializable
    data object Search : MainScreens

    @Serializable
    data object Favorites : MainScreens

    @Serializable
    data class CharacterDetail(val id: Int) : MainScreens

}