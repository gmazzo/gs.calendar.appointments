val buildConfigVersion: String by settings
val kotlinVersion: String by settings

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when {
                requested.id.id == "org.jetbrains.kotlin.frontend" ->
                    useModule("org.jetbrains.kotlin:kotlin-frontend-plugin:${requested.version}")

                requested.id.id == "kotlin2js" ->
                    useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

                requested.id.id == "kotlinx-serialization" ->
                    useModule("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")

                requested.id.id.startsWith("org.jetbrains.kotlin") ->
                    useVersion(kotlinVersion)

                requested.id.id == "com.github.gmazzo.buildconfig" ->
                    useVersion(buildConfigVersion)
            }
        }
    }
    repositories {
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://kotlin.bintray.com/kotlin-eap")
    }
}

include("model", "core", "backend", "frontend", "webapp-bundle")
