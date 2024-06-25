package se.sabumbi.kmptemplate.auth.firebase

import io.ktor.server.auth.*


class FirebasePrincipal(
    val userId: String = "",
    val displayName: String = "",
    val email: String = ""
) : Principal
