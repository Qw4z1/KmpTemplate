package se.sabumbi.kmptemplate

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.app
import dev.gitlive.firebase.initialize

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        val name = Firebase.app.name
        println("Firebase app name: $name")
        AppInitializer.onApplicationStart(AppContext(this))
    }
}