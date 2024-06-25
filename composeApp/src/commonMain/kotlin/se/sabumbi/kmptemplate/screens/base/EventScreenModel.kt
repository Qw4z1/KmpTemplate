package se.sabumbi.kmptemplate.screens.base

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class EventScreenModel<S, E>(initialState: S) : StateScreenModel<S>(initialState) {
    protected val eventsChannel = Channel<E>()
    val events = eventsChannel.receiveAsFlow() // expose as flow

    fun sendEvent(event: E) {
        screenModelScope.launch {
            eventsChannel.send(event)
        }
    }
}
