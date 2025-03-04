package model.response

import kotlinx.serialization.Serializable

/**
 * Response model to expose in API response
 */
@Serializable
sealed interface Response {
    val status: State
    val message: String
}

/**
 * HTTP Response Status. Used for evaluation of [HttpResponse] type.
 */
enum class State {
    SUCCESS, NOT_FOUND, FAILED, UNAUTHORIZED
}