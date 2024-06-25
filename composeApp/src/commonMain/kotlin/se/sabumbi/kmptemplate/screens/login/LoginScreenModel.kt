package se.sabumbi.kmptemplate.screens.login

import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import se.sabumbi.kmptemplate.data.UserRepository
import se.sabumbi.kmptemplate.screens.base.EventScreenModel

sealed class Event {
    data object OnboardingComplete : Event()
}

sealed class LoginError {
    data object UserCollision : LoginError()
    data object InvalidCredentials : LoginError()
    data object WeakPassword : LoginError()
    data object Unknown : LoginError()
}

sealed class State {
    data object Default : State()
}

class LoginScreenModel(private val userRepository: UserRepository) :
    EventScreenModel<State, Event>(initialState = State.Default) {
    private val log = Logger.withTag("🔒LoginScreenModel")
    fun login(result: Result<FirebaseUser?>) {
        if (result.isSuccess) {
            screenModelScope.launch {
                val apiUser = userRepository.login()
                log.d { "apiUser: $apiUser" }
                sendEvent(Event.OnboardingComplete)
            }
        } else {
            log.e { "Error logging in: ${result.exceptionOrNull()}" }
            // TODO handle error
        }
    }

}