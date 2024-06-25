package se.sabumbi.kmptemplate

import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider

object AppInitializer {
    fun onApplicationStart(appContext: AppContext) {
        onApplicationStartPlatformSpecific(appContext)
        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = "602047542746-amr9uumvcilia2d61rqvsk2fd6g11fb1.apps.googleusercontent.com"))
    }
}

expect class AppContext

expect fun onApplicationStartPlatformSpecific(appContext: AppContext)