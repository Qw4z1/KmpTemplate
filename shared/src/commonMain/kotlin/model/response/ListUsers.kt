package model.response

import kotlinx.serialization.Serializable
import model.ApiUser

@Serializable
data class ListUsers(
    override val status: State,
    override val message: String,
    val users: List<ApiUser>? = null
) : Response {

        companion object {

            fun notFound(message: String) = ListUsers(
                State.NOT_FOUND,
                message
            )

            fun success(message: String, users: List<ApiUser>?) = ListUsers(
                State.SUCCESS,
                message,
                users
            )
        }
}
