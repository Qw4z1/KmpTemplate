package model.response

import kotlinx.serialization.Serializable
import model.ApiUser

@Serializable
data class AuthResponse(
    override val status: State,
    override val message: String,
    val user: ApiUser? = null
) : Response {

    companion object {

        fun notFound(message: String) = AuthResponse(
            State.NOT_FOUND,
            message
        )

        fun created(message: String, user: ApiUser) = AuthResponse(
            State.SUCCESS,
            message,
            user
        )

        fun loggedIn(message: String, user: ApiUser) = AuthResponse(
            State.SUCCESS,
            message,
            user
        )
    }
}
