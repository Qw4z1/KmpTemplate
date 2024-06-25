import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

import org.jetbrains.compose.ui.tooling.preview.Preview
import se.sabumbi.kmptemplate.components.FullScreenLoadingContent
import se.sabumbi.kmptemplate.data.UserRepository
import se.sabumbi.kmptemplate.screens.home.HomeScreen
import se.sabumbi.kmptemplate.screens.login.LoginScreen
import se.sabumbi.kmptemplate.theme.AppTheme

// TODO dependency injection
val client: HttpClient by lazy { configureHttpClient() }

private fun configureHttpClient(): HttpClient =
    HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        defaultRequest {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
            url {
                protocol = URLProtocol.HTTP
                // TODO get host from config
                host = if (getPlatform().type == PlatformType.ANDROID) "10.0.2.2" else "localhost"
                port = 8080
            }
        }
    }

val userRepository by lazy { UserRepository(client) }

sealed class LoggedInState {
    data object Loading : LoggedInState()
    data object LoggedIn : LoggedInState()
    data object LoggedOut : LoggedInState()
}

private val log = Logger.withTag("👾App")

@Composable
@Preview
fun App() {
    var loggedInState by remember { mutableStateOf<LoggedInState>(LoggedInState.Loading) }
    LaunchedEffect(Unit) {
        val isLoggedIn = userRepository.isLoggedIn.first()
        log.d { "Auth state changed $isLoggedIn" }
        loggedInState =
            if (isLoggedIn == true) LoggedInState.LoggedIn else LoggedInState.LoggedOut
    }

    log.d { "Logged in: $loggedInState" }

    AppTheme {
        if (loggedInState == LoggedInState.Loading) {
            FullScreenLoadingContent()
            return@AppTheme
        }
        BottomSheetNavigator {
            val root = if (LoggedInState.LoggedIn == loggedInState) {
                HomeScreen()
            } else {
                LoginScreen()
            }
            Navigator(root)
        }
    }
}