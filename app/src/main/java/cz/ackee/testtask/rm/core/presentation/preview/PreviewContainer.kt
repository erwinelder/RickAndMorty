package cz.ackee.testtask.rm.core.presentation.preview

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.presentation.theme.AndroidTaskRickAndMortyTheme
import cz.ackee.testtask.rm.core.presentation.theme.AppColors

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PreviewContainer(
    appTheme: AppTheme = AppTheme.Light,
    content: @Composable BoxScope.() -> Unit
) {
    BoxWithConstraints {
        SharedTransitionLayout {
            AndroidTaskRickAndMortyTheme(
                boxWithConstraintsScope = this@BoxWithConstraints,
                isSystemInDarkTheme = appTheme == AppTheme.Dark
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColors.background)
                ) {
                    content()
                }
            }
        }
    }
}