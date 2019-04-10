plugins {
    application
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig")
}

val generateBuildConfig by tasks
val jar: Jar by tasks
val installDist: Sync by tasks

dependencies {
    implementation(project(":backend"))
}

application {
    mainClassName = "gs.calendar.appointments.WebappMainKt"
}

jar.manifest { attributes["Main-Class"] = application.mainClassName }

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

task("stage") {
    doFirst {
        val command = file("${installDist.destinationDir}/bin/${project.name}").relativeTo(rootDir)

        file("$rootDir/Procfile").writeText("web: RESTEASY_PORT=\$PORT $command")
    }
    dependsOn(installDist)
}
