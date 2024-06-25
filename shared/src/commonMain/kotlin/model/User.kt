package model

import kotlinx.serialization.Serializable


@Serializable
data class ApiUser(
    val id: String,
    val name: String,
    val email: String,
)
