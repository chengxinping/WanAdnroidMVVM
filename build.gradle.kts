import java.net.URI

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        maven {
            setUrl("https://maven.aliyun.com/nexus/content/repositories/google")
        }
        jcenter()

    }
    dependencies {
        classpath(BuildPlugins.androidGradle)
        classpath(BuildPlugins.kotlinGradle)
        classpath(BuildPlugins.koinGradle)
        classpath(BuildPlugins.navigationGradle)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.ktx files
    }
}

allprojects {
    repositories {
        maven {
            setUrl("https://maven.aliyun.com/nexus/content/repositories/google")
        }
        jcenter()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
