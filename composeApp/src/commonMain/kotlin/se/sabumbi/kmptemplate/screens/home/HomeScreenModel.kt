package se.sabumbi.kmptemplate.screens.home

import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.launch
import model.ApiUser
import se.sabumbi.kmptemplate.data.UserNotFoundException
import se.sabumbi.kmptemplate.data.UserNotLoggedInException
import se.sabumbi.kmptemplate.screens.base.EventScreenModel
import userRepository

sealed class Event {
    data class OnboardingComplete(val user: String) : Event()
    data object LoggedOut : Event()
}

sealed class State {
    data object Loading : State()
    data class Error(val message: String) : State()
    data class Default(val user: ApiUser) : State()
}

class HomeScreenModel : EventScreenModel<State, Event>(initialState = State.Loading) {
    fun logoutUser() {
        screenModelScope.launch {
            try {
                userRepository.logout()
                sendEvent(Event.LoggedOut)
            } catch (e: UserNotLoggedInException) {
                log.e(e) { "User not logged in" }
                sendEvent(Event.LoggedOut)
            }
        }
    }

    fun startRecording() {
        TODO("Not yet implemented")
    }

    private val log = Logger.withTag("🏠 HomeScreenModel")

    init {
        screenModelScope.launch {
            try {
                log.d { "Loading user..." }
                val user = userRepository.getUser()

                // TODO fetch notes

                log.d { "User found: $user" }
                mutableState.value = State.Default(user)
            } catch (e: UserNotFoundException) {
                screenModelScope.launch {
                    log.e(e) { "User doesn't exist anymore. Logout user." }
                    userRepository.logout()
                    sendEvent(Event.LoggedOut)
                }
            }
        }
    }
}