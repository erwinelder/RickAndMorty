package cz.ackee.testtask.rm.core.presentation.preview

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.MainScaffold
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import cz.ackee.testtask.rm.core.presentation.theme.AndroidTaskRickAndMortyTheme
import cz.ackee.testtask.rm.di.initializeKoinMockedModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PreviewWithMainScaffoldContainer(
    appTheme: AppTheme = AppTheme.Light,
    isBottomBarVisible: Boolean = true,
    koinConfig: KoinAppDeclaration? = null,
    koinModuleDeclaration: Module.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    runCatching {
        initializeKoinMockedModule(config = koinConfig) {
            viewModel {
                NavViewModel(
                    isBottomBarVisible = isBottomBarVisible
                )
            }
            koinModuleDeclaration()
        }
    }

    BoxWithConstraints {
        SharedTransitionLayout {
            AndroidTaskRickAndMortyTheme(
                boxWithConstraintsScope = this@BoxWithConstraints,
                isSystemInDarkTheme = appTheme == AppTheme.Dark
            ) {
                MainScaffold(
                    content = content
                )
            }
        }
    }
}