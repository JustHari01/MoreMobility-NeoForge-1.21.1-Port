import org.gradle.language.jvm.tasks.ProcessResources

plugins {
    java
    id("net.neoforged.moddev") version "2.0.148"
}

group = "com.sysnote8"
version = providers.gradleProperty("mod_version").get()

base {
    archivesName.set("moremobility")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

sourceSets.main {
    resources.srcDir("src/generated/resources")
    resources.srcDir(layout.buildDirectory.dir("generated/mixin"))
}

neoForge {
    version = providers.gradleProperty("neo_version").get()

    runs {
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.INFO
        }
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", "moremobility")
        }
        create("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", "moremobility")
        }
        create("data") {
            data()
            programArguments.addAll(
                "--mod", "moremobility", "--all",
                "--output", file("src/generated/resources").absolutePath,
                "--existing", file("src/main/resources").absolutePath,
            )
        }
    }

    mods {
        create("moremobility") {
            sourceSet(sourceSets.main.get())
        }
    }
}

repositories {
    maven("https://cursemaven.com")
    maven("https://maven.terraformersmc.com/releases/")
    maven("https://maven.blamejared.com/")
}

dependencies {
    implementation("curse.maven:automobility-unofficial-1702208:8917683")

    compileOnly("dev.emi:emi-neoforge:1.1.24+1.21.1:api")
    compileOnly("mezz.jei:jei-1.21.1-neoforge-api:19.21.0.247")

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

val metadata = mapOf(
    "minecraft_version" to providers.gradleProperty("minecraft_version").get(),
    "minecraft_version_range" to providers.gradleProperty("minecraft_version_range").get(),
    "loader_version_range" to providers.gradleProperty("loader_version_range").get(),
    "neo_version_range" to providers.gradleProperty("neo_version_range").get(),
    "mod_id" to "moremobility",
    "mod_name" to "MoreMobility (UNOFFICIAL)",
    "mod_license" to "MIT",
    "mod_version" to project.version.toString(),
    "mod_authors" to "sysnote8, port by JustHari01",
    "mod_description" to "Unofficial NeoForge port of MoreMobility by BlueShapes. Adds a Netherite engine, Silver and Race Shopping Cart frames, an Advanced Crop Harvester, and configurable off-road speed reduction for Automobility. Prepared with AI assistance.",
)

val generateModMetadata = tasks.register<ProcessResources>("generateModMetadata") {
    inputs.properties(metadata)
    filteringCharset = "UTF-8"
    from("src/main/templates")
    into(layout.buildDirectory.dir("generated/sources/modMetadata"))
    expand(metadata)
}
sourceSets.main { resources.srcDir(generateModMetadata) }
neoForge.ideSyncTask(generateModMetadata)
tasks.named<ProcessResources>("processResources") {
    dependsOn(tasks.named("compileJava"))
    // NeoForge data generation leaves an incremental .cache file beside generated resources.
    // It is build metadata, not a runtime mod resource.
    exclude(".cache/**", "**/.cache/**")
}

tasks.jar {
    from("LICENSE") {
        into("META-INF")
    }
    manifest.attributes(
        "Specification-Title" to "moremobility",
        "Specification-Vendor" to "sysnote8; NeoForge port by JustHari01",
        "Specification-Version" to "1",
        "Implementation-Title" to "MoreMobility NeoForge Port",
        "Implementation-Version" to project.version.toString(),
        "Implementation-Vendor" to "sysnote8; NeoForge port by JustHari01",
        "MixinConfigs" to "moremobility.mixins.json",
    )
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add(
        "-AoutRefMapFile=${layout.buildDirectory.file("generated/mixin/moremobility.refmap.json").get().asFile.absolutePath}"
    )
}
