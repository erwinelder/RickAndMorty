package cz.ackee.testtask.rm.character.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import cz.ackee.testtask.rm.R
import cz.ackee.testtask.rm.character.presentation.model.CharacterDetailUiState
import cz.ackee.testtask.rm.character.presentation.viewmodel.CharacterDetailViewModel
import cz.ackee.testtask.rm.core.domain.app.AppTheme
import cz.ackee.testtask.rm.core.domain.app.FilledWidthByScreenType
import cz.ackee.testtask.rm.core.presentation.component.container.TopBarContainer
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.ScreenScaffold
import cz.ackee.testtask.rm.core.presentation.component.text.TopBarTitle
import cz.ackee.testtask.rm.core.presentation.navigation.model.MainScreens
import cz.ackee.testtask.rm.core.presentation.preview.PreviewWithMainScaffoldContainer
import cz.ackee.testtask.rm.core.presentation.theme.AppColors
import cz.ackee.testtask.rm.core.presentation.theme.CurrWindowType
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CharacterDetailScreenWrapper(
    screenPadding: PaddingValues = PaddingValues(),
    navBackStackEntry: NavBackStackEntry,
    navController: NavController
) {
    val characterId = navBackStackEntry.toRoute<MainScreens.CharacterDetail>().id
    val viewModel = koinViewModel<CharacterDetailViewModel> {
        parametersOf(characterId)
    }

    val character by viewModel.character.collectAsStateWithLifecycle()

    CharacterDetailScreen(
        screenPadding = screenPadding,
        onNavigateBack = navController::popBackStack,
        state = character,
        onChangeFavouriteStatus = viewModel::changeFavouriteStatus
    )
}

@Composable
fun CharacterDetailScreen(
    screenPadding: PaddingValues = PaddingValues(),
    onNavigateBack: () -> Unit,
    state: CharacterDetailUiState,
    onChangeFavouriteStatus: () -> Unit
) {
    ScreenScaffold(
        screenPadding = screenPadding,
        topBar = {
            TopBar(
                onNavigateBack = onNavigateBack,
                name = state.name,
                isFavourite = state.isFavourite,
                onChangeFavouriteStatus = onChangeFavouriteStatus
            )
        }
    ) { padding ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth(FilledWidthByScreenType(.96f).get(CurrWindowType))
                    .background(AppColors.surface)
                    .padding(vertical = 16.dp)
            ) {
                CharacterPhotoWithNameContainer(
                    imageUrl = state.imageUrl,
                    name = state.name,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = AppColors.onSurface,
                    modifier = Modifier.graphicsLayer(alpha = .1f)
                )
                CharacterInfoContainer(
                    state = state,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    onNavigateBack: () -> Unit,
    name: String,
    isFavourite: Boolean,
    onChangeFavouriteStatus: () -> Unit
) {
    TopBarContainer(
        padding = PaddingValues(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
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

        TopBarTitle(text = name)

        IconButton(
            onClick = onChangeFavouriteStatus
        ) {
            Icon(
                painter = painterResource(
                    if (isFavourite) R.drawable.favorites else R.drawable.favorites_enabled
                ),
                contentDescription = stringResource(R.string.favorites),
                tint = if (isFavourite) AppColors.primary else AppColors.onSurface
            )
        }

    }
}

@Composable
private fun CharacterPhotoWithNameContainer(
    imageUrl: String?,
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.character_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.name),
                color = AppColors.onSurface,
                fontSize = 20.sp,
                fontWeight = FontWeight.W400
            )
            Text(
                text = name,
                color = AppColors.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700
            )
        }
    }
}

@Composable
private fun CharacterInfoContainer(
    state: CharacterDetailUiState,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        CharacterInfoBlock(title = stringResource(R.string.status), value = state.status)
        CharacterInfoBlock(title = stringResource(R.string.species), value = state.species)
        CharacterInfoBlock(title = stringResource(R.string.type), value = state.type)
        CharacterInfoBlock(title = stringResource(R.string.gender), value = state.gender)
        CharacterInfoBlock(title = stringResource(R.string.origin), value = state.origin)
        CharacterInfoBlock(title = stringResource(R.string.location), value = state.location)
    }
}

@Composable
private fun CharacterInfoBlock(
    title: String,
    value: String
) {
    Column {
        Text(
            text = title,
            color = AppColors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400
        )
        Text(
            text = value,
            color = AppColors.onSurface,
            fontSize = 19.sp,
            fontWeight = FontWeight.W700
        )
    }
}



@Preview(device = PIXEL_7_PRO)
@Composable
private fun CharacterDetailScreenPreview() {
    val character = CharacterDetailUiState(
        id = 1,
        imageUrl = null,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "-",
        gender = "Male",
        origin = "Earth (C-137)",
        location = "Earth (Replacement Dimension)",
        isFavourite = true
    )

    PreviewWithMainScaffoldContainer(appTheme = AppTheme.Light) { screenPadding ->
        CharacterDetailScreen(
            screenPadding = screenPadding,
            onNavigateBack = {},
            state = character,
            onChangeFavouriteStatus = {}
        )
    }
}