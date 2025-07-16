package cz.ackee.testtask.rm.presentation.navigation.model

import kotlinx.serialization.Serializable

sealed interface MainScreens {

    @Serializable
    data object Characters : MainScreens

    @Serializable
    data object Search : MainScreens

    @Serializable
    data object FavoriteCharacters : MainScreens

    @Serializable
    data object CharacterDetail : MainScreens

}