package se.sabumbi.kmptemplate

import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider

object AppInitializer {
    fun onApplicationStart(appContext: AppContext) {
        onApplicationStartPlatformSpecific(appContext)
        TODO("Add actual server ID below")
        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = ""))
    }
}

expect class AppContext

expect fun onApplicationStartPlatformSpecific(appContext: AppContext)