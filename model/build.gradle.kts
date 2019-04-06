import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    kotlin("multiplatform")
}

val kotlinCoroutinesVersion: String by project

fun kotlinCoroutines(module: String, version: String = kotlinCoroutinesVersion) =
    "org.jetbrains.kotlinx:kotlinx-coroutines-$module:$version"


kotlin {
    jvm()
    js()

    sourceSets {
        commonMain {
            dependencies {
                api(kotlin("stdlib-common"))
                api(kotlinCoroutines("core-common"))
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                api(kotlin("stdlib-jdk8"))
                api(kotlinCoroutines("jdk8"))
            }
        }

        js().compilations["main"].defaultSourceSet {
            dependencies {
                api(kotlin("stdlib-js"))
                api(kotlinCoroutines("core-js"))
            }
        }
    }
}

tasks.withType(Kotlin2JsCompile::class) {
    kotlinOptions {
        moduleKind = "commonjs"
    }
}
