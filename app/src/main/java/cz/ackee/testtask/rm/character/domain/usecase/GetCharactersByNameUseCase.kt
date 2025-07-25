package cz.ackee.testtask.rm.character.domain.usecase

import androidx.paging.PagingData
import cz.ackee.testtask.rm.character.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface GetCharactersByNameUseCase {

    suspend fun execute(name: String): Flow<PagingData<Character>>

}