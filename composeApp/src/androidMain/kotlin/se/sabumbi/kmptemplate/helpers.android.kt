package se.sabumbi.kmptemplate

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

actual fun onApplicationStartPlatformSpecific(appContext: AppContext) {
    Firebase.initialize(appContext.context)
}

actual class AppContext(val context: Application)