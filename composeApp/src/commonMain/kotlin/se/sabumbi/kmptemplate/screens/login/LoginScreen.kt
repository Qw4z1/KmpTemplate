package se.sabumbi.kmptemplate.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import dev.gitlive.firebase.auth.FirebaseUser
import se.sabumbi.kmptemplate.screens.home.HomeScreen
import userRepository

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val screenmodel = rememberScreenModel { LoginScreenModel(userRepository) }
        HandleEvents(screenmodel)
        AuthUiHelperButtonsAndFirebaseAuth(
            onFirebaseResult = {
                screenmodel.login(it)
            },
        )
    }

    @Composable
    private fun HandleEvents(screenmodel: LoginScreenModel) {
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(Unit) {
            screenmodel.events.collect {
                when (it) {
                    Event.OnboardingComplete -> navigator.replaceAll(HomeScreen())
                }
            }
        }
    }
    @Composable
    fun AuthUiHelperButtonsAndFirebaseAuth(
        modifier: Modifier = Modifier,
        onFirebaseResult: (Result<FirebaseUser?>) -> Unit,
    ) {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {

            // TODO build own GoogleSignInButton
            //Google Sign-In Button and authentication with Firebase
            GoogleButtonUiContainerFirebase(onResult = onFirebaseResult) {
                GoogleSignInButton(modifier = Modifier.fillMaxWidth()) { this.onClick() }
            }
//
//            //Apple Sign-In Button and authentication with Firebase
//            AppleButtonUiContainer(onResult = onFirebaseResult) {
//                AppleSignInButton(modifier = Modifier.fillMaxWidth()) { this.onClick() }
//            }
        }
    }
}