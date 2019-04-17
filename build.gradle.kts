import com.github.gmazzo.gradle.plugins.tasks.BuildConfigTask
import org.jetbrains.gradle.ext.ProjectSettings
import org.jetbrains.gradle.ext.TaskTriggersConfig

plugins {
    kotlin("jvm") apply false
    kotlin("frontend") apply false
    id("com.github.gmazzo.buildconfig") apply false
    id("org.jetbrains.gradle.plugin.idea-ext") version "0.5"
}

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

idea {
    project {
        this as ExtensionAware

        configure<ProjectSettings> {
            this as ExtensionAware

            // binds any buildConfig task to Idea's Gradle afterSync trigger
            configure<TaskTriggersConfig> {
                allprojects {
                    tasks.withType(BuildConfigTask::class) {
                        afterSync(this)
                    }
                }
            }
        }
    }
}
