plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "itmo.tpo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test-junit5"))

    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
//    testImplementation("org.mockito:mockito-inline:2.8.47")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}