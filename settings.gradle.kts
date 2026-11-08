pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.neoforged.net/releases") {
            name = "NeoForged"
        }
        maven("https://repo.spongepowered.org/repository/maven-public/") {
            name = "SpongePowered"
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.8.0")
}

rootProject.name = "moremobility-neoforge-1.21.1"
