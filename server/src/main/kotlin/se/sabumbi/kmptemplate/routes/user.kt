package se.sabumbi.kmptemplate.routes

import io.ktor.server.application.call
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import model.response.UserResponse
import se.sabumbi.kmptemplate.auth.firebase.FirebasePrincipal
import se.sabumbi.kmptemplate.database.UserService

fun Route.users() {
    val userService = UserService()
    route(Routes.User.me) {
        get {
            val principal = call.principal<FirebasePrincipal>()
            if (principal == null) {
                call.respond(UserResponse.notFound("User not found"))
                return@get
            }
            println("User found: ${principal.userId}")
            try {
                val user = userService.getUserById(principal.userId)
                call.respond(UserResponse.success("User found", user))
            } catch (e: Exception) {
                val message = e.message ?: "User not found"
                println("Error: $message")
                call.respond(UserResponse.notFound("User not found"))
            }
        }
    }
}