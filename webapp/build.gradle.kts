import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
    id("com.github.gmazzo.buildconfig") version "1.2.1"
}

val daggerVersion: String by project
val restEasyVersion: String by project

dependencies {
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation(project(":core"))

    implementation(kotlin("stdlib"))
    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("io.swagger.core.v3:swagger-jaxrs2:2.0.7")
    implementation("io.undertow.jastow:jastow:2.1.0.Final")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("javax.servlet.jsp:javax.servlet.jsp-api:2.3.3")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider:$restEasyVersion")
    implementation("org.jboss.resteasy:resteasy-undertow:$restEasyVersion")
}

buildConfig {
    buildConfigField("String", "APP_NAME", "\"$group\"")
    buildConfigField("String", "API_CONTEXT", "\"api\"")
    buildConfigField("String", "ADMIN_USER_ID", "\"gmazzo65@gmail.com\"")
    buildConfigField(
        type = "java.io.File",
        name = "DATA_STORE_FILE",
        value = "File(\"${rootProject.buildDir.relativeTo(rootDir)}/storage\")"
    )
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

    create("generateResourcesConstants") {
        doFirst {
            sourceSets["main"].resources.asFileTree
                .visit(Action<FileVisitDetails> {
                    val name = path.toUpperCase().replace("\\W".toRegex(), "_")

                    buildConfig.buildConfigField("String", "RESOURCE_$name", "\"$path\"")
                })
        }

        tasks["generateBuildConfig"].dependsOn(this)
    }

}
