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
    implementation("io.swagger.core.v3:swagger-jaxrs2:2.0.7")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider:$restEasyVersion")
    implementation("org.jboss.resteasy:resteasy-netty4:$restEasyVersion")
}

kapt {
    correctErrorTypes = true
}

tasks {
    withType(KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
