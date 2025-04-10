plugins {
    kotlin("jvm") version "2.1.10" apply false
    id("java")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

group = "io.github.hyungjunn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
