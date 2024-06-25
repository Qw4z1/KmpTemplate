package se.sabumbi.kmptemplate.database

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.util.*


@Serializable
data class ExposedNote(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val title: String,
    val content: String
)

class NotesService {
    object Notes : UUIDTable() {
        val title = varchar("title", length = 50)
        val content = text("content")
    }

    suspend fun create(note: ExposedNote): EntityID<UUID> = dbQuery {
        Notes.insert {
            it[title] = note.title
            it[content] = note.content
        }[Notes.id]
    }

    suspend fun read(id: UUID): ExposedNote? {
        return dbQuery {
            Notes.select { Notes.id eq id }
                .map { ExposedNote(it[Notes.id].value, it[Notes.title], it[Notes.content]) }
                .singleOrNull()
        }
    }

    suspend fun update(id: UUID, user: ExposedNote) {
        dbQuery {
            Notes.update({ Notes.id eq id }) {
                it[title] = user.title
                it[content] = user.content
            }
        }
    }

    suspend fun delete(id: UUID) {
        dbQuery {
            Notes.deleteWhere { Notes.id.eq(id) }
        }
    }
}