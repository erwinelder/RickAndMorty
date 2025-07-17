package cz.ackee.testtask.rm.request.domain.model

sealed interface Result<out S: RootSuccess?, out E: RootError> {
    data class Success<out S: RootSuccess?, out E: RootError>(val success: S): Result<S, E>
    data class Error<out S: RootSuccess?, out E: RootError>(val error: E): Result<S, E>
}