allprojects {
    repositories {
        jcenter()
    }

    group = "gs.calendar.appointments"
    version = "1.0-SNAPSHOT"

}

task<Delete>("clean") {
    delete(buildDir)
}
