import com.github.gmazzo.gradle.plugins.BuildConfigLanguage
import com.github.gmazzo.gradle.plugins.tasks.BuildConfigTask
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
    kotlin("frontend") version "0.0.45"
    id("com.github.gmazzo.buildconfig")
}

val appName: String by project
val apiEndpoint: String? by project
val reactVersion = "16.6.0"
val reactReduxVersion = "5.0.7"
val kotlinVersion: String by project
val kotlinWrappersVersion = "pre.69-kotlin-$kotlinVersion"
val kotlinReactVersion = "$reactVersion-$kotlinWrappersVersion"

dependencies {
    implementation(project(":model"))

    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
    implementation("org.jetbrains:kotlin-react:$kotlinReactVersion")
    implementation("org.jetbrains:kotlin-react-dom:$kotlinReactVersion")
    implementation("org.jetbrains:kotlin-react-redux:$reactReduxVersion-$kotlinWrappersVersion")
    implementation("org.jetbrains:kotlin-styled:1.0.0-$kotlinWrappersVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion")
}

buildConfig {
    language(BuildConfigLanguage.KOTLIN)
    packageName("${project.group}.${project.name}")

    buildConfigField("String", "APP_NAME", "\"$appName\"")
    buildConfigField("String", "API_ENDPOINT", "\"${apiEndpoint ?: "api"}\"")
    buildConfigField(
        "String",
        "API_CLIENT_ID",
        "\"752118259594-201e8779d52re6d2lr2pkrca4fjt2tbj.apps.googleusercontent.com\""
    )
}

kotlinFrontend {
    sourceMaps = true
    downloadNodeJsVersion = "6.9.0"

    npm {
        dependency("@material-ui/core", "3.9.3")
        dependency("@material-ui/icons", "3.0.2")
        dependency("inline-style-prefixer", "5.0.4")
        dependency("less", "3.9.0")
        dependency("moment", "2.24.0")
        dependency("notistack", "0.6.0")
        dependency("react", reactVersion)
        dependency("react-big-calendar", "0.20.4")
        dependency("react-dom", reactVersion)
        dependency("react-google-login", "5.0.4")
        dependency("react-redux", reactReduxVersion)
        dependency("redux", "4.0.1")
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
        sourceMapEnabled = true
    }

}

tasks.withType(Kotlin2JsCompile::class) {
    kotlinOptions {
        metaInfo = true
        outputFile = "$buildDir/js/$name.js"
        sourceMap = true
        sourceMapEmbedSources = "always"
        moduleKind = "commonjs"
        main = "call"
    }
}

tasks.withType(BuildConfigTask::class) {
    addGeneratedAnnotation = false

    tasks["compileKotlin2Js"].dependsOn(this)
}
