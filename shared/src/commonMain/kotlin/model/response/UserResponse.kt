package model.response

import kotlinx.serialization.Serializable
import model.ApiUser

@Serializable
data class UserResponse(
    override val status: State,
    override val message: String,
    val user: ApiUser? = null
) : Response {

    companion object {

        fun notFound(message: String) = UserResponse(
            State.NOT_FOUND,
            message
        )

        fun success(message: String, user: ApiUser) = UserResponse(
            State.SUCCESS,
            message,
            user
        )
    }
}
