import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.github.gmazzo.buildconfig")
}

val kotlinxSerializationVersion: String by project

kotlin {
    jvm()
    js()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib-common"))
                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinxSerializationVersion")
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                api(kotlin("stdlib-jdk8"))
                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinxSerializationVersion")
            }
        }

        js().compilations["main"].defaultSourceSet {
            dependencies {
                api(kotlin("stdlib-js"))
                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$kotlinxSerializationVersion")
            }
        }
    }
}

buildConfig {
    buildConfigField("String", "HTTP_HEADER_AUTH_TOKEN_ID", "\"Google-Token-Id\"")
    buildConfigField(
        "String",
        "API_CLIENT_ID",
        System.getenv("GOOGLE_CLIENT_ID")?.let {"\"$it\""}
            ?: file("google_client_id.txt").takeIf { it.isFile }?.readText()?.let {"\"$it\""}
            ?: "\"\" // TODO put a valid ClientId  here"
    )
}

tasks.withType(Kotlin2JsCompile::class) {
    kotlinOptions {
        moduleKind = "commonjs"
    }
}
