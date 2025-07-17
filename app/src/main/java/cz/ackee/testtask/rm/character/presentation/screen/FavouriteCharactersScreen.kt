package cz.ackee.testtask.rm.character.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.character.presentation.component.CharacterComponentFilled
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import cz.ackee.testtask.rm.character.presentation.viewmodel.FavouriteCharactersViewModel
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.presentation.component.container.TopBarContainer
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.ScreenScaffold
import cz.ackee.testtask.rm.core.presentation.component.text.MessageComponent
import cz.ackee.testtask.rm.core.presentation.component.text.TopBarTitle
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import cz.ackee.testtask.rm.core.presentation.preview.PreviewWithMainScaffoldContainer
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavouriteCharactersScreenWrapper(
    screenPadding: PaddingValues = PaddingValues(),
    navController: NavController,
    navViewModel: NavViewModel
) {
    val viewModel = koinViewModel<FavouriteCharactersViewModel>()

    val characters by viewModel.characters.collectAsStateWithLifecycle()

    FavouriteCharactersScreen(
        screenPadding = screenPadding,
        characters = characters,
        onCharacterClick = { id ->
            navViewModel.navigateToScreen(
                navController = navController,
                screen = MainScreens.CharacterDetail(id = id)
            )
        }
    )
}

@Composable
fun FavouriteCharactersScreen(
    screenPadding: PaddingValues = PaddingValues(),
    characters: List<CharacterUiState>,
    onCharacterClick: (id: Int) -> Unit
) {
    ScreenScaffold(
        screenPadding = screenPadding,
        topBar = {
            TopBar()
        }
    ) { padding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            items(characters) { character ->
                CharacterComponentFilled(
                    state = character,
                    onClick = onCharacterClick
                )
            }
        }
        if (characters.isEmpty()) {
            MessageComponent(
                text = stringResource(R.string.no_favourites_yet),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }
}

@Composable
private fun TopBar() {
    TopBarContainer(
        padding = PaddingValues(horizontal = 16.dp, vertical = 14.dp)
    ) {
        TopBarTitle(text = stringResource(R.string.favorites))
    }
}



@Preview(device = PIXEL_7_PRO)
@Composable
private fun FavouriteCharactersScreenPreview() {
    val characters = listOf(
        CharacterUiState(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            isFavourite = true
        ),
        CharacterUiState(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
        )
    )

    PreviewWithMainScaffoldContainer(appTheme = AppTheme.Light) { screenPadding ->
        FavouriteCharactersScreen(
            screenPadding = screenPadding,
            characters = characters,
//            characters = emptyList(),
            onCharacterClick = {}
        )
    }
}