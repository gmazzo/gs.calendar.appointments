plugins {
    id("com.github.node-gradle.node") version "1.3.0"
}

task("build") {
    dependsOn("npm_run_build")
}

task<Delete>("clean") {
    delete(buildDir)
}
