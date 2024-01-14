plugins {
    id("java")
    id("org.jetbrains.dokka") version "1.9.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Mockito
    testImplementation("org.mockito:mockito-core:3.11.2")
}


tasks.test {
    useJUnitPlatform()
}

tasks.dokkaJavadoc{

}
tasks.register<Jar>("cronparser.jar") {
    archiveBaseName.set("cronparser-application")
    archiveVersion.set("2.0")

    manifest {
        attributes["Main-Class"] = "org.example.CronExpressionParser"
    }
    from(sourceSets.main.get().output)
}