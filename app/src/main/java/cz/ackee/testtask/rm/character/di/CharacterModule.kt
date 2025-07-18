package cz.ackee.testtask.rm.character.di

import cz.ackee.testtask.rm.character.data.local.source.CharacterLocalDataSource
import cz.ackee.testtask.rm.character.data.local.source.characterLocalDataSourceFactory
import cz.ackee.testtask.rm.character.data.remote.source.CharacterRemoteDataSource
import cz.ackee.testtask.rm.character.data.remote.source.CharacterRemoteDataSourceImpl
import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.data.repository.CharacterRepositoryImpl
import cz.ackee.testtask.rm.character.domain.usecase.AddFavoriteCharacterUseCase
import cz.ackee.testtask.rm.character.domain.usecase.AddFavoriteCharacterUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetCharacterByIdUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetCharacterByIdUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersByNameUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersByNameUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharacterIdsUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharacterIdsUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharactersUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharactersUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.IsCharacterFavoriteUseCase
import cz.ackee.testtask.rm.character.domain.usecase.IsCharacterFavoriteUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.RemoveFavoriteCharacterUseCase
import cz.ackee.testtask.rm.character.domain.usecase.RemoveFavoriteCharacterUseCaseImpl
import cz.ackee.testtask.rm.character.presentation.viewmodel.CharacterDetailViewModel
import cz.ackee.testtask.rm.character.presentation.viewmodel.CharactersViewModel
import cz.ackee.testtask.rm.character.presentation.viewmodel.FavouriteCharactersViewModel
import cz.ackee.testtask.rm.character.presentation.viewmodel.SearchCharactersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {

    /* ---------- Sources ---------- */

    single<CharacterLocalDataSource> {
        characterLocalDataSourceFactory(appDatabase = get())
    }

    single<CharacterRemoteDataSource> {
        CharacterRemoteDataSourceImpl(client = get())
    }

    /* ---------- Repositories ---------- */

    single<CharacterRepository> {
        CharacterRepositoryImpl(
            localSource = get(),
            remoteSource = get()
        )
    }

    /* ---------- Use Cases ---------- */

    single<AddFavoriteCharacterUseCase> {
        AddFavoriteCharacterUseCaseImpl(repository = get())
    }

    single<RemoveFavoriteCharacterUseCase> {
        RemoveFavoriteCharacterUseCaseImpl(repository = get())
    }

    single<GetCharactersUseCase> {
        GetCharactersUseCaseImpl(repository = get())
    }

    single<GetCharactersByNameUseCase> {
        GetCharactersByNameUseCaseImpl(repository = get())
    }

    single<GetFavoriteCharactersUseCase> {
        GetFavoriteCharactersUseCaseImpl(repository = get())
    }

    single<GetFavoriteCharacterIdsUseCase> {
        GetFavoriteCharacterIdsUseCaseImpl(repository = get())
    }

    single<GetCharacterByIdUseCase> {
        GetCharacterByIdUseCaseImpl(repository = get())
    }

    single<IsCharacterFavoriteUseCase> {
        IsCharacterFavoriteUseCaseImpl(repository = get())
    }

    /* ---------- View Models ---------- */

    viewModel {
        CharactersViewModel(
            getFavoriteCharacterIdsUseCase = get(),
            getCharactersUseCase = get()
        )
    }

    viewModel {
        SearchCharactersViewModel(
            getFavoriteCharacterIdsUseCase = get(),
            getCharactersByNameUseCase = get()
        )
    }

    viewModel {
        FavouriteCharactersViewModel(getFavoriteCharactersUseCase = get())
    }

    viewModel { params ->
        CharacterDetailViewModel(
            id = params.get(),
            getCharacterByIdUseCase = get(),
            isCharacterFavoriteUseCase = get(),
            addFavoriteCharacterUseCase = get(),
            removeFavoriteCharacterUseCase = get()
        )
    }

}