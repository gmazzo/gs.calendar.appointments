import com.github.gmazzo.gradle.plugins.BuildConfigLanguage
import com.github.gmazzo.gradle.plugins.tasks.BuildConfigTask
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
    kotlin("frontend") version "0.0.45"
    id("com.github.gmazzo.buildconfig")
}

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    maven(url = "https://dl.bintray.com/kotlinx/kotlinx")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

val appName: String by project
val reactVersion = "16.6.0"
val kotlinVersion: String by project
val kotlinWrappersVersion = "pre.69-kotlin-$kotlinVersion"
val kotlinReactVersion = "$reactVersion-$kotlinWrappersVersion"

dependencies {
    implementation(project(":model"))
    implementation(kotlin("stdlib-js"))

    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
    implementation("org.jetbrains:kotlin-react:$kotlinReactVersion")
    implementation("org.jetbrains:kotlin-react-dom:$kotlinReactVersion")
    implementation("org.jetbrains:kotlin-styled:1.0.0-$kotlinWrappersVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion")
}

buildConfig {
    language(BuildConfigLanguage.KOTLIN)
    packageName("${project.group}.${project.name}")

    buildConfigField("String", "APP_NAME", "\"$appName\"")
}

kotlinFrontend {
    sourceMaps = true

    npm {
        dependency("@material-ui/core", "3.9.2")
        dependency("@material-ui/icons", "3.0.2")
        dependency("axios", "0.18.0")
        dependency("inline-style-prefixer", "5.0.4")
        dependency("less", "3.9.0")
        dependency("moment", "2.24.0")
        dependency("react", reactVersion)
        dependency("react-big-calendar", "0.20.4")
        dependency("react-dom", reactVersion)
        dependency("styled-components", "4.2.0")

        devDependency("karma", "4.0.1")
        devDependency("style-loader", "0.23.1")
        devDependency("css-loader", "2.1.1")
        devDependency("less-loader", "4.1.0")
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

tasks.withType(BuildConfigTask::class) {
    addGeneratedAnnotation = false

    tasks["compileKotlin2Js"].dependsOn(this)
}
