plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
}

val daggerVersion: String by project

dependencies {
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation(kotlin("stdlib"))
    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("com.google.apis:google-api-services-calendar:v3-rev364-1.25.0")

    testCompile("junit:junit:4.12")
}
