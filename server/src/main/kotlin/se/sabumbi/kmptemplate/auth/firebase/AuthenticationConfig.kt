package se.sabumbi.kmptemplate.auth.firebase

import io.ktor.server.auth.*

fun AuthenticationConfig.firebase(
    realm: String,
    configure: FirebaseConfig.() -> Unit
) {
    val provider = FirebaseAuthProvider(FirebaseConfig(realm).apply(configure))
    register(provider)
}