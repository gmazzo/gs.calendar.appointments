plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
}

val daggerVersion: String by project
val googleClientVersion: String by project

dependencies {
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    compile("com.google.apis:google-api-services-calendar:v3-rev364-$googleClientVersion")

    implementation(kotlin("stdlib"))
    implementation("com.google.dagger:dagger:$daggerVersion")

    testCompile("junit:junit:4.12")
}
