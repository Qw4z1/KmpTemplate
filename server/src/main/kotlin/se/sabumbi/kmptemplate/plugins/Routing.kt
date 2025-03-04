package se.sabumbi.kmptemplate.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.*
import se.sabumbi.kmptemplate.routes.authRoutes
import se.sabumbi.kmptemplate.routes.debugRoute
import se.sabumbi.kmptemplate.routes.users

fun Application.configureRouting() {
    val realm = environment.config.property("jwt.realm").getString()

    routing {
        authenticate(realm) {
            authRoutes()
            users()
        }
        // TODO if development
        debugRoute()
    }
}