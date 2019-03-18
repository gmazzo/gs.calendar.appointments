import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
    kotlin("frontend") version "0.0.45"
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
    implementation(project(":model"))
    implementation(kotlin("stdlib-js"))
    
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
    implementation("org.jetbrains:kotlin-react:$kotlinReactVersion")
    implementation("org.jetbrains:kotlin-react-dom:$kotlinReactVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion")
}

kotlinFrontend {
    downloadNodeJsVersion = "latest"
    sourceMaps = true

    npm {
        dependency("@material-ui/core")
        dependency("axios")
        dependency("less")
        dependency("moment")
        dependency("react", reactVersion)
        dependency("react-big-calendar")
        dependency("react-dom", reactVersion)

        devDependency("karma")
        devDependency("style-loader")
        devDependency("css-loader")
        devDependency("less-loader")
    }

    bundle("webpack") {
        this as WebPackExtension // TODO this should not be required once the frontend plugin is stable
        bundleName = "main"
        contentPath = file("src/main/web")
        mode = "development"
    }

}

tasks.withType(Kotlin2JsCompile::class) {
    kotlinOptions {
        metaInfo = true
        outputFile = "$buildDir/js/$name.js"
        sourceMap = true
        moduleKind = "commonjs"
        main = "call"
    }
}
