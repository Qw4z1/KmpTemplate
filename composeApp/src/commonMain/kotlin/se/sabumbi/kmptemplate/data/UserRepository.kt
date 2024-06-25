package se.sabumbi.kmptemplate.data

import Routes
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import model.ApiUser
import model.response.AuthResponse
import model.response.ListUsers

class UserRepository(
    private val client: HttpClient = HttpClient()
) {

    val isLoggedIn: Flow<Boolean?>
        get() = Firebase.auth.authStateChanged.map {
            it?.getIdToken(false) != null
        }

    private val token: Flow<String?>
        get() = Firebase.auth.authStateChanged.map {
            it?.getIdToken(false)
        }

    suspend fun login(): ApiUser {
        val token = token.first() ?: throw UserNotLoggedInException()
        val response = client.post("auth/register") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }.body<AuthResponse>()
        return response.user ?: throw UserNotFoundException()
    }

    suspend fun getUser(): ApiUser {
        val token = token.first() ?: throw UserNotLoggedInException()
        val response = client.get(Routes.User.me) {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }.body<AuthResponse>()
        return response.user ?: throw UserNotFoundException()
    }

    suspend fun getAllUsers(): List<ApiUser> {
        val token = token.first() ?: throw UserNotLoggedInException()
        val response = client.get(Routes.Debug.users) {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }.body<ListUsers>()
        return response.users ?: throw UserNotFoundException()
    }

    suspend fun logout() {
        Firebase.auth.signOut()
    }

}

class UserNotFoundException : Exception()
class UserNotLoggedInException : Exception()
