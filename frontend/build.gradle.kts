plugins {
    id("com.moowork.node") version "1.2.0"
}

task("build") {
    dependsOn("npm_run_build")
}

task<Delete>("clean") {
    delete(buildDir)
}
