package se.sabumbi.kmptemplate.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.response.ListUsers
import se.sabumbi.kmptemplate.database.UserService

fun Route.debugRoute() {
    val userService = UserService()
    route(Routes.Debug.users) {
        get {
            val users = userService.getAllUsers()
            call.respond(ListUsers.success("Users found", users))
        }
    }

    route(Routes.Debug.health) {
        get {
            call.respondText("OK")
        }
    }
}