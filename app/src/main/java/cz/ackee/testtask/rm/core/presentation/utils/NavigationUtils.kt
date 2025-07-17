package cz.ackee.testtask.rm.core.presentation.utils

import androidx.navigation.NavBackStackEntry
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import kotlin.reflect.KClass


fun NavBackStackEntry?.fromRoute(): String {
    return this?.destination?.route
        ?.substringBefore('/')?.substringAfterLast('.')?.substringBefore("?") ?: ""
}

fun NavBackStackEntry?.fromMainScreen(): MainScreens? {
    this.fromRoute().let { route ->
        return when (route) {
            MainScreens.Characters::class.simpleName() -> MainScreens.Characters
            MainScreens.Search::class.simpleName() -> MainScreens.Search
            MainScreens.Favorites::class.simpleName() -> MainScreens.Favorites
            MainScreens.CharacterDetail::class.simpleName() -> MainScreens.CharacterDetail(id = 0)
            else -> null
        }
    }
}

fun KClass<out Any>.simpleName(): String? {
    return this.simpleName?.substringAfterLast('$')?.substringBefore("(")
}
