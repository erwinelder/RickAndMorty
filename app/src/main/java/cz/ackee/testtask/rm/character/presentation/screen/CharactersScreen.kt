package cz.ackee.testtask.rm.character.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.character.presentation.component.CharacterComponentFilled
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import cz.ackee.testtask.rm.character.presentation.viewmodel.CharactersViewModel
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.presentation.component.container.TopBarContainer
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.ScreenScaffold
import cz.ackee.testtask.rm.core.presentation.component.text.TopBarTitle
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import cz.ackee.testtask.rm.core.presentation.preview.PreviewWithMainScaffoldContainer
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import kotlinx.coroutines.flow.flowOf
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharactersScreenWrapper(
    screenPadding: PaddingValues = PaddingValues(),
    navController: NavController,
    navViewModel: NavViewModel
) {
    val viewModel = koinViewModel<CharactersViewModel>()

    val characters = viewModel.characters.collectAsLazyPagingItems()

    CharactersScreen(
        screenPadding = screenPadding,
        onSearchButtonClick = {
            navViewModel.navigateToScreen(
                navController = navController, screen = MainScreens.Search
            )
        },
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
fun CharactersScreen(
    screenPadding: PaddingValues = PaddingValues(),
    onSearchButtonClick: () -> Unit,
    characters: LazyPagingItems<CharacterUiState>,
    onCharacterClick: (id: Int) -> Unit
) {
    ScreenScaffold(
        screenPadding = screenPadding,
        topBar = {
            TopBar(onSearchButtonClick = onSearchButtonClick)
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
            items(characters.itemCount) { index ->
                characters[index]?.let { character ->
                    CharacterComponentFilled(
                        state = character,
                        onClick = onCharacterClick
                    )
                }
            }
            when (characters.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                is LoadState.Error -> {
                    item {
                        Text(
                            text = "Error loading more items",
                            color = AppColors.onSurface,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                else -> {}
            }
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
                tint = AppColors.onSurface
            )
        }
    }
}



@Preview(device = PIXEL_7_PRO)
@Composable
private fun CharactersScreenPreview() {
    val list = listOf(
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
    val characters = flowOf(PagingData.from(list)).collectAsLazyPagingItems()

    PreviewWithMainScaffoldContainer(appTheme = AppTheme.Light) { screenPadding ->
        CharactersScreen(
            screenPadding = screenPadding,
            onSearchButtonClick = {},
            characters = characters,
            onCharacterClick = {}
        )
    }
}