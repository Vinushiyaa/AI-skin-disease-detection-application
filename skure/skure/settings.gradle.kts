pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Skure"
include(":app")

// Enable Foojay toolchain resolver so Gradle can auto-download JDKs
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}


