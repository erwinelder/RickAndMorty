package cz.ackee.testtask.rm.character.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.presentation.component.container.TopBarContainer
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.ScreenScaffold
import cz.ackee.testtask.rm.core.presentation.component.text.TopBarTitle
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import cz.ackee.testtask.rm.core.presentation.preview.PreviewWithMainScaffoldContainer

@Composable
fun CharactersScreenWrapper(
    screenPadding: PaddingValues = PaddingValues(),
    navController: NavController,
    navViewModel: NavViewModel,
) {
    CharactersScreen(
        screenPadding = screenPadding,
        onSearchButtonClick = {
            navViewModel.navigateToScreen(
                navController = navController, screen = MainScreens.Search
            )
        }
    )
}

@Composable
fun CharactersScreen(
    screenPadding: PaddingValues = PaddingValues(),
    onSearchButtonClick: () -> Unit
) {
    ScreenScaffold(
        screenPadding = screenPadding,
        topBar = {
            TopBar(onSearchButtonClick = onSearchButtonClick)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {

        }
    }
}

@Composable
private fun TopBar(
    onSearchButtonClick: () -> Unit
) {
    TopBarContainer(
        padding = PaddingValues(start = 16.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
    ) {
        TopBarTitle(text = stringResource(R.string.characters))
        IconButton(
            onClick = onSearchButtonClick
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(R.string.search),
            )
        }
    }
}



@Preview(device = PIXEL_7_PRO)
@Composable
private fun CharactersScreenPreview() {
    PreviewWithMainScaffoldContainer(appTheme = AppTheme.Light) { screenPadding ->
        CharactersScreen(
            screenPadding = screenPadding,
            onSearchButtonClick = {}
        )
    }
}