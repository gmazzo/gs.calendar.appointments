val kotlinVersion: String by settings

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.jetbrains.kotlin.frontend") {
                useModule("org.jetbrains.kotlin:kotlin-frontend-plugin:${requested.version}")

            } else if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

            } else if (requested.id.id.startsWith("org.jetbrains.kotlin.")) {
                useVersion(kotlinVersion)
            }
        }
    }
    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

include("model","core", "webapp", "frontend")
