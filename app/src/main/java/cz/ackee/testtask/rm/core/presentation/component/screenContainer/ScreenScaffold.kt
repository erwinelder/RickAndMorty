package cz.ackee.testtask.rm.core.presentation.component.screenContainer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScreenScaffold(
    screenPadding: PaddingValues = PaddingValues(),
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topBar,
        containerColor = Color.Transparent,
        modifier = Modifier.padding(screenPadding)
    ) { padding ->
        content(padding)
    }
}