plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxSerialization)
    application
}

group = "se.sabumbi.kmptemplate"
version = "1.0.0"
application {
    mainClass.set("se.sabumbi.kmptemplate.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.firebase.admin)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.psql)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}