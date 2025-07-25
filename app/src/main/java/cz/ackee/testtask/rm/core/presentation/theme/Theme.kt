package cz.ackee.testtask.rm.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.domain.app.WindowType

val LocalAppTheme = compositionLocalOf { AppTheme.Light }
val LocalColors = compositionLocalOf<AppPalette> { AppPalette.Light }
val LocalTypography = compositionLocalOf { AppCustomTypography() }
val LocalWindowType = compositionLocalOf { WindowType.Compact }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidTaskRickAndMortyTheme(
    boxWithConstraintsScope: BoxWithConstraintsScope,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val appTheme = if (isSystemInDarkTheme) AppTheme.Dark else AppTheme.Light
    val appColors = if (isSystemInDarkTheme) AppPalette.Dark else AppPalette.Light
    val typography = AppCustomTypography()
    val windowType = when {
        boxWithConstraintsScope.maxWidth < 600.dp -> WindowType.Compact
        boxWithConstraintsScope.maxWidth < 840.dp -> WindowType.Medium
        else -> WindowType.Expanded
    }

    CompositionLocalProvider(
        LocalAppTheme provides appTheme,
        LocalColors provides appColors,
        LocalTypography provides typography,
        LocalWindowType provides windowType,
        LocalRippleConfiguration provides null
    ) {
        MaterialTheme(content = content)
    }
}

val CurrAppTheme: AppTheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAppTheme.current

val AppColors: AppPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

val AppTypography: AppCustomTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current

val CurrWindowType: WindowType
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current

val WindowTypeIsCompact: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current == WindowType.Compact

val WindowTypeIsMedium: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current == WindowType.Medium

val WindowTypeIsExpanded: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current == WindowType.Expanded
