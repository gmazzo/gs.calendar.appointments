plugins {
    application
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig")
}

dependencies {
    implementation(project(":backend"))
}

application {
    mainClassName = "gs.calendar.appointments.WebappMainKt"
}

val copyFrontendBuild = task<Copy>("copyFrontendBuild") {
    val frontend = evaluationDependsOn(":frontend")
    val frontendBundleTask = frontend.tasks["bundle"]
    val outputDir = file("$buildDir/frontend/public")

    dependsOn(frontendBundleTask)
    from(frontend.file("${frontend.buildDir}/bundle"))
    from(frontend.file("src/main/web"))
    into(outputDir)

    sourceSets["main"].resources.srcDir(outputDir.parentFile)
}

val generateBuildConfig by tasks

task("generateResourcesConstants") {
    val resConfig = buildConfig.forClass("Resources")

    dependsOn(copyFrontendBuild)
    doFirst {
        sourceSets["main"].resources.asFileTree
            .visit(Action<FileVisitDetails> {
                val name = path.toUpperCase().replace("\\W".toRegex(), "_")

                resConfig.buildConfigField("String", name, "\"$path\"")
            })
    }

    generateBuildConfig.dependsOn(this)
}
