package se.sabumbi.kmptemplate.database

import model.ApiUser
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class UserService {
    object Users : Table() {
        val id = varchar("id", length = 50)
        val name = varchar("name", length = 50)
        val email = varchar("email", length = 50)
        override val primaryKey: PrimaryKey = PrimaryKey(id)
    }

    suspend fun create(user: ApiUser): ApiUser = dbQuery {
        Users.insert {
            it[id] = user.id
            it[name] = user.name
            it[email] = user.email
        }[Users.id]
        user
    }

    suspend fun read(id: String): ApiUser? {
        return dbQuery {
            Users.select { Users.id eq id }
                .map {
                    ApiUser(
                        it[Users.id],
                        it[Users.name],
                        it[Users.email]
                    )
                }
                .singleOrNull()
        }
    }

    suspend fun update(id: String, user: ApiUser) {
        dbQuery {
            Users.update({ Users.id eq id }) {
                it[name] = user.name
            }
        }
    }

    suspend fun delete(id: String) {
        dbQuery {
            Users.deleteWhere { Users.id.eq(id) }
        }
    }

    suspend fun userByEmail(email: String): ApiUser {
        return dbQuery {
            Users.select { Users.email eq email }
                .map {
                    ApiUser(
                        it[Users.id],
                        it[Users.name],
                        it[Users.email]
                    )
                }
                .single()
        }
    }

    suspend fun getAllUsers(): List<ApiUser> {
        return dbQuery {
            Users.selectAll()
                .map {
                    ApiUser(
                        it[Users.id],
                        it[Users.name],
                        it[Users.email]
                    )
                }
        }
    }

    suspend fun getUserById(userId: String): ApiUser =
        dbQuery {
            Users.select { Users.id eq userId }
                .map {
                    ApiUser(
                        it[Users.id],
                        it[Users.name],
                        it[Users.email]
                    )
                }
                .single()
        }
}

