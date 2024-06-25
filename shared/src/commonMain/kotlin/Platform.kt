interface Platform {
    val name: String
    val type: PlatformType
}

enum class PlatformType {
    ANDROID,
    IOS,
    WEB,
    DESKTOP
}

expect fun getPlatform(): Platform