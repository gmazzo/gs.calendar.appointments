import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
    id("org.jetbrains.kotlin.frontend") version "0.0.45"
}

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    maven(url = "https://dl.bintray.com/kotlinx/kotlinx")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

val reactVersion: String by project
val kotlinVersion: String by project
val kotlinReactVersion = "$reactVersion-pre.69-kotlin-$kotlinVersion"

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-js:$kotlinVersion")
    compile("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
    compile("org.jetbrains:kotlin-react:$kotlinReactVersion")
    compile("org.jetbrains:kotlin-react-dom:$kotlinReactVersion")

    testCompile("org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion")
}

// FIXME for some reason the 'kotlin-frontend-plugin' doesn't compile on KTS
apply(from = "build-compat.gradle")

tasks.withType(Kotlin2JsCompile::class) {
    kotlinOptions {
        metaInfo = true
        outputFile = "$buildDir/js/$name.js"
        sourceMap = true
        moduleKind = "commonjs"
        main = "call"
    }
}
