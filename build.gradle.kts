allprojects {

    repositories {
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://kotlin.bintray.com/kotlin-js-wrappers")
    }

    group = "gs.calendar.appointments"
    version = "1.0-SNAPSHOT"

}

task<Delete>("clean") {
    delete(buildDir)
}
