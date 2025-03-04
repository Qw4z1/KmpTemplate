package se.sabumbi.kmptemplate.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import se.sabumbi.kmptemplate.controller.AuthController

fun Route.authRoutes() {
    val authController = AuthController()
    post(Routes.Auth.signUpAndIn) {
        val response = authController.idpAuthentication(
            call
        ).let { response ->
            call.respond(response)
        }

        call.respond(response)
    }
}