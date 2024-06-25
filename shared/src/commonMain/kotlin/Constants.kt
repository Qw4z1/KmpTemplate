@file:Suppress("ConstPropertyName")

const val SERVER_PORT = 8080


object Routes {
    object Auth {
        const val signUpAndIn = "/auth/register"
    }

    object User {
        const val me = "/users/me"
    }

    object Debug {
        const val health = "/debug/health"
        const val users = "/debug/users"
    }

}