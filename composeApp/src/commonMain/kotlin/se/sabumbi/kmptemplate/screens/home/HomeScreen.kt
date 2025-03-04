package se.sabumbi.kmptemplate.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import model.ApiUser
import org.jetbrains.compose.resources.painterResource
import se.sabumbi.kmptemplate.components.UserCard
import se.sabumbi.kmptemplate.screens.login.LoginScreen
import kmptemplate.composeapp.generated.resources.Res
import kmptemplate.composeapp.generated.resources.mic_24px

class HomeScreen : Screen {

    private val log = Logger.withTag("🏠 HomeScreen")

    @Composable
    override fun Content() {
        log.d { "In HomeScreen" }
        val screenmodel = rememberScreenModel { HomeScreenModel() }
        val state by screenmodel.state.collectAsState()
        HandleEvents(screenmodel)

        log.d { "State: $state" }
        when (state) {
            is State.Loading -> {
                // TODO loading
            }

            is State.Default -> {
                HomeScreenContent(
                    user = (state as State.Default).user,
                    screenmodel::logoutUser,
                    screenmodel::startRecording
                )
            }

            is State.Error -> Text("Error: ${(state as State.Error).message}")
        }

    }

    @Composable
    private fun HandleEvents(screenmodel: HomeScreenModel) {
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(Unit) {
            screenmodel.events.collect {
                log.d { "Event: $it" }
                when (it) {
                    is Event.LoggedOut -> {
                        navigator.replace(LoginScreen())
                    }

                    is Event.OnboardingComplete -> TODO()
                }
            }
        }
    }

    @Composable
    private fun HomeScreenContent(
        user: ApiUser,
        onLogout: () -> Unit,
        startRecording: () -> Unit
    ) {
        log.d { "User: $user" }
        Scaffold(
            topBar = {
                UserCard(user = user, onLogout = onLogout)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = startRecording) {
                    Image(
                        painterResource(Res.drawable.mic_24px),
                        contentDescription = "Start recording"
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(
                    color = androidx.compose.ui.graphics.Color.White
                )
            ) {
                // TODO list notes
            }
        }

    }
}