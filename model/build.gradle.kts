plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    js()

    sourceSets {
        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}
