package se.sabumbi.kmptemplate.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import se.sabumbi.kmptemplate.auth.firebase.firebase
import se.sabumbi.kmptemplate.auth.firebase.FirebasePrincipal

fun Application.configureFirebaseAuth() {
    val realm = environment.config.property("jwt.realm").getString()
    install(Authentication) {
        firebase(realm) {
            validate { token ->
                token.claims["name"]?.let { name ->
                    FirebasePrincipal(email = token.email, userId = token.uid, displayName = name as String)
                } ?: FirebasePrincipal(email = token.email, userId = token.uid, displayName = "Unknown")
            }
        }
    }
}