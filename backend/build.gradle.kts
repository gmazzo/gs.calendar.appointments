import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets
import java.util.Base64

plugins {
    application
    kotlin("jvm")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("com.github.gmazzo.buildconfig")
}

val appName: String by project
val daggerVersion: String by project
val restEasyVersion = "4.0.0.Beta8"

dependencies {
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation(project(":core"))

    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("com.jakewharton:jax-rs-kotlinx-serialization:0.2.1")
    implementation("io.swagger.core.v3:swagger-jaxrs2:2.0.7")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.jboss.resteasy:resteasy-undertow:$restEasyVersion")
}

application {
    mainClassName = "gs.calendar.appointments.MainKt"
}

buildConfig {
    buildConfigField("String", "APP_NAME", "\"$appName\"")
    buildConfigField("String", "API_CONTEXT", "\"api\"")
    buildConfigField(
        type = "java.io.File",
        name = "DATA_STORE_FILE",
        value = "File(\"${rootProject.buildDir.relativeTo(rootDir)}/storage\")"
    )
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val generateBuildConfig by tasks

val generateResourcesConstants = task("generateResourcesConstants") {
    val resConfig = buildConfig.forClass("Resources")

    doFirst {
        sourceSets["main"].resources.asFileTree
            .visit(Action<FileVisitDetails> {
                val name = path.toUpperCase().replace("\\W".toRegex(), "_")

                resConfig.buildConfigField("String", name, "\"$path\"")
            })
    }

    generateBuildConfig.dependsOn(this)
}

task("retrieveCredentialsFromEnv") {
    val propertyValue = System.getenv("GOOGLE_CREDENTIALS") ?: ""
    val resourceFile = file("src/main/resources/google_client_secrets.json")

    inputs.property("credentials", propertyValue)
    outputs.file(resourceFile)
    generateResourcesConstants.dependsOn(this)

    onlyIf { propertyValue.isNotBlank() || !resourceFile.exists() }
    doFirst {
        resourceFile.writeText(
            if (propertyValue.isNotBlank()) Base64.getDecoder()
                .decode(propertyValue)
                .toString(StandardCharsets.UTF_8)
            else
                "// TODO put a valid Service Account credential here"
        )
    }
}
