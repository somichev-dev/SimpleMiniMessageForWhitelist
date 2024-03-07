plugins {
    kotlin("jvm") version "1.9.22"
}

group = "dev.somichev"
version = "1.0.0"
description = "MiniMessage for whitelist"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // https://mvnrepository.com/artifact/com.google.guava/guava
    implementation("com.google.guava:guava:33.0.0-jre")
    // https://mvnrepository.com/artifact/org.yaml/snakeyaml
    implementation("org.yaml:snakeyaml:2.2")
    // https://mvnrepository.com/artifact/io.netty/netty-all
    implementation("io.netty:netty-all:4.1.107.Final")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
        val props =
            mapOf(
                "name" to project.name,
                "version" to project.version,
                "description" to project.description,
                "apiVersion" to "1.19",
            )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
kotlin {
    jvmToolchain(17)
}
