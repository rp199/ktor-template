plugins {
    id("com.rp199.kotlin-application-conventions")
    alias(libs.plugins.kotlin.serilization)
    alias(libs.plugins.ktor)
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.logging)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.hoplite)
    implementation(libs.micrometer.registry.prometheus)

    testImplementation(libs.bundles.test)
    testImplementation(libs.ktor.server.test.host.jvm)
}

application {
    // Define the main class for the application.
    mainClass.set("com.rp199.app.AppKt")
}
