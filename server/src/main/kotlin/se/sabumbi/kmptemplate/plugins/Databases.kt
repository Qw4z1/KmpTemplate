package se.sabumbi.kmptemplate.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import se.sabumbi.kmptemplate.database.NotesService
import se.sabumbi.kmptemplate.database.UserService


fun Application.configureDatabases() {
    val url = environment.config.property("psql.url").getString()
    val user = environment.config.property("psql.user").getString()
    val password = environment.config.property("psql.password").getString()
    try {
        val database = Database.connect(
            url = "jdbc:$url",
            user = user,
            password = password
        )
        transaction(database) {
            SchemaUtils.create(UserService.Users)
            SchemaUtils.create(NotesService.Notes)
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
        throw e
    }
}
