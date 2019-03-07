import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("war")
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
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider:$restEasyVersion")
    implementation("org.jboss.resteasy:resteasy-netty4:$restEasyVersion")
}

buildConfig {
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
            val resources = sourceSets["main"].resources

            resources.files.forEach {
                val name = it.name.toUpperCase().replace("\\W".toRegex(), "_")
                val path = it.relativeTo(resources.srcDirs.iterator().next())

                buildConfig.buildConfigField("String", "RESOURCE_$name", "\"/$path\"")
            }
        }

        tasks["generateBuildConfig"].dependsOn(this)
    }

}
