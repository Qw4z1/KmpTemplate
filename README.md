## This is a Kotlin Multiplatform project targeting Android, iOS, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

## Libraries used

* [Ktor](https://github.com/ktorio/ktor) for the server and http client.
* [Firebase Authentication](https://github.com/GitLiveApp/firebase-kotlin-sdk) for authentication.
* [Voyager](https://github.com/adrielcafe/voyager) for navigation.
* [Exposed](https://github.com/JetBrains/Exposed) for database access.
* [KMPAuth](https://github.com/mirzemehdi/KMPAuth) for client side sign in.

## Credentials
To run the server, you need to create a `credentials.json` file and place it in the `server/src/main/resources` folder. To create this file got to the Firebase Console -> Project Settings -> Service Accounts -> Generate new private key.

To run the Android app, you need to create a `google-services.json` file and place it in the `composeApp/android` folder. To create this file go to the Firebase Console -> Project Settings -> General -> Your apps -> Add app -> Android. 

To run the iOS app, you need to create a `GoogleService-Info.plist` file and place it in the `iosApp` folder. To create this file go to the Firebase Console -> Project Settings -> General -> Your apps -> Add app -> iOS.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…