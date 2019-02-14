import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("war")
    id("org.springframework.boot") version "2.1.2.RELEASE"
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.spring")
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    project(":core")

    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
