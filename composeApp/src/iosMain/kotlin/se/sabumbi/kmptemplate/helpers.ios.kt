package se.sabumbi.kmptemplate

import co.touchlab.kermit.Logger
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.apps
import dev.gitlive.firebase.initialize



actual fun onApplicationStartPlatformSpecific(appContext: AppContext) {

    try {
        if (Firebase.apps().isEmpty()) {
            Firebase.initialize()
        }

    } catch (e: Exception) {
        Logger.e("Firebase init", e)
    }
}


actual class AppContext()