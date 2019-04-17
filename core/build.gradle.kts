plugins {
    kotlin("jvm")
    kotlin("kapt")
}

val daggerVersion: String by project
val googleClientVersion: String by project

dependencies {
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    api(project(":model"))
    api("com.google.http-client:google-http-client:$googleClientVersion")

    implementation("com.google.apis:google-api-services-people:v1-rev427-$googleClientVersion")
    implementation("com.google.apis:google-api-services-calendar:v3-rev364-$googleClientVersion")
    implementation("com.google.dagger:dagger:$daggerVersion")

    testCompile("junit:junit:4.12")
}
