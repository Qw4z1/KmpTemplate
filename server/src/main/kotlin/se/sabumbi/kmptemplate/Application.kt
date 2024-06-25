package se.sabumbi.kmptemplate

import io.ktor.server.application.*
import io.ktor.server.netty.*
import se.sabumbi.kmptemplate.auth.firebase.FirebaseAdmin
import se.sabumbi.kmptemplate.plugins.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    FirebaseAdmin.init()
    configureSerialization()
    configureMonitoring()
    configureDatabases()
    configureFirebaseAuth()
    configureRouting()
}