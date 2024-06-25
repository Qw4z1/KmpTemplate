package se.sabumbi.kmptemplate.controller

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import model.ApiUser
import se.sabumbi.kmptemplate.database.UserService
import se.sabumbi.kmptemplate.auth.firebase.FirebasePrincipal
import model.response.AuthResponse
import model.response.GeneralResponse
import model.response.Response

class AuthController {
    private val userService = UserService()
    suspend fun idpAuthentication(
        ctx: ApplicationCall
    ): Response {
        return try {
            val principal = ctx.principal<FirebasePrincipal>() ?: return GeneralResponse.failed("Authentication failed")

            val user = userService.read(principal.userId)
            if (principal.userId == user?.id) {
                AuthResponse.loggedIn("User signed in successfully", user)
            } else {
                val createdUser: ApiUser = userService.create(principal.toApiUser())
                AuthResponse.created("User successfully created", createdUser)
            }
        } catch (e: BadRequestException) {
            GeneralResponse.failed(e.message ?: "Authentication failed")
        }
    }
}

private fun FirebasePrincipal.toApiUser(): ApiUser {
    return ApiUser(
        id = this.userId,
        name = this.displayName,
        email = this.email
    )
}
