import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("war")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
}

val daggerVersion: String by project
val restEasyVersion: String by project

dependencies {
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation(project(":core"))

    implementation(kotlin("stdlib"))
    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("org.jboss.resteasy:resteasy-netty4:$restEasyVersion")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider:$restEasyVersion")
}

kapt {
    correctErrorTypes = true
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
