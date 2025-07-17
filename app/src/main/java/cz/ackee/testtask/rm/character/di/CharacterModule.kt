package cz.ackee.testtask.rm.character.di

import cz.ackee.testtask.rm.character.data.local.source.CharacterLocalDataSource
import cz.ackee.testtask.rm.character.data.local.source.characterLocalDataSourceFactory
import cz.ackee.testtask.rm.character.data.remote.source.CharacterRemoteDataSource
import cz.ackee.testtask.rm.character.data.remote.source.CharacterRemoteDataSourceImpl
import cz.ackee.testtask.rm.character.data.repository.CharacterRepository
import cz.ackee.testtask.rm.character.data.repository.CharacterRepositoryImpl
import cz.ackee.testtask.rm.character.domain.usecase.AddFavouriteCharacterUseCase
import cz.ackee.testtask.rm.character.domain.usecase.AddFavouriteCharacterUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.DeleteFavouriteCharacterUseCase
import cz.ackee.testtask.rm.character.domain.usecase.DeleteFavouriteCharacterUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetCharacterByIdUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetCharacterByIdUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersByNameUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersByNameUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetFavouriteCharacterIdsUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetFavouriteCharacterIdsUseCaseImpl
import cz.ackee.testtask.rm.character.domain.usecase.GetFavouriteCharactersUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetFavouriteCharactersUseCaseImpl
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

    single<AddFavouriteCharacterUseCase> {
        AddFavouriteCharacterUseCaseImpl(repository = get())
    }

    single<DeleteFavouriteCharacterUseCase> {
        DeleteFavouriteCharacterUseCaseImpl(repository = get())
    }

    single<GetCharactersUseCase> {
        GetCharactersUseCaseImpl(repository = get())
    }

    single<GetCharactersByNameUseCase> {
        GetCharactersByNameUseCaseImpl(repository = get())
    }

    single<GetFavouriteCharactersUseCase> {
        GetFavouriteCharactersUseCaseImpl(repository = get())
    }

    single<GetFavouriteCharacterIdsUseCase> {
        GetFavouriteCharacterIdsUseCaseImpl(repository = get())
    }

    single<GetCharacterByIdUseCase> {
        GetCharacterByIdUseCaseImpl(repository = get())
    }

    /* ---------- View Models ---------- */

    viewModel {
        CharactersViewModel(
            getFavouriteCharacterIdsUseCase = get(),
            getCharactersUseCase = get()
        )
    }

    viewModel {
        SearchCharactersViewModel(
            getFavouriteCharacterIdsUseCase = get(),
            getCharactersByNameUseCase = get()
        )
    }

    viewModel {
        FavouriteCharactersViewModel(getFavouriteCharactersUseCase = get())
    }

}