package cz.ackee.testtask.rm.character.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.character.presentation.component.CharacterComponent
import cz.ackee.testtask.rm.character.presentation.model.CharacterUiState
import cz.ackee.testtask.rm.character.presentation.viewmodel.SearchCharactersViewModel
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.presentation.component.container.TopBarContainer
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.ScreenScaffold
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import cz.ackee.testtask.rm.core.presentation.preview.PreviewWithMainScaffoldContainer
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import cz.ackee.testtask.rm.core.presentation.theme.AppTypography
import kotlinx.coroutines.flow.flowOf
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchCharactersScreenWrapper(
    screenPadding: PaddingValues = PaddingValues(),
    navController: NavController,
    navViewModel: NavViewModel
) {
    val viewModel = koinViewModel<SearchCharactersViewModel>()

    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()
    val characters = viewModel.characters.collectAsLazyPagingItems()

    SearchCharactersScreen(
        screenPadding = screenPadding,
        searchQuery = searchQuery.value,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onNavigateBack = navController::popBackStack,
        characters = characters,
        onCharacterClick = { id ->
            navViewModel.navigateToScreen(
                navController = navController,
                screen = MainScreens.CharacterDetail(id)
            )
        }
    )
}

@Composable
fun SearchCharactersScreen(
    screenPadding: PaddingValues = PaddingValues(),
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onNavigateBack: () -> Unit,
    characters: LazyPagingItems<CharacterUiState>,
    onCharacterClick: (id: Int) -> Unit
) {
    val currentFocus = LocalFocusManager.current

    ScreenScaffold(
        screenPadding = screenPadding,
        topBar = {
            TopBar(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onNavigateBack = {
                    currentFocus.clearFocus()
                    onNavigateBack()
                }
            )
        }
    ) { padding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .padding(padding)
                .clickable { currentFocus.clearFocus() }
                .fillMaxSize()
        ) {
            items(characters.itemCount) { index ->
                characters[index]?.let { character ->
                    CharacterComponent(
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
                else -> {}
            }
        }
    }
}

@Composable
private fun TopBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    TopBarContainer(
        padding = PaddingValues(start = 4.dp, end = 16.dp, top = 0.dp, bottom = 0.dp)
    ) {
        IconButton(
            onClick = onNavigateBack
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = stringResource(R.string.back),
                tint = AppColors.onSurface
            )
        }
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_characters),
                    style = AppTypography.field,
                    color = AppColors.outline
                )
            },
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = AppTypography.field,
            colors = TextFieldDefaults.colors(
                focusedTextColor = AppColors.onSurface,
                unfocusedTextColor = AppColors.onSurface,
                focusedIndicatorColor = AppColors.primary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedPlaceholderColor = AppColors.outline,
                unfocusedPlaceholderColor = AppColors.outline,
            )
        )
    }
}



@Preview(device = PIXEL_7_PRO)
@Composable
private fun SearchCharactersScreenPreview() {
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
        SearchCharactersScreen(
            screenPadding = screenPadding,
            searchQuery = "",
            onSearchQueryChange = {},
            onNavigateBack = {},
            characters = characters,
            onCharacterClick = {}
        )
    }
}