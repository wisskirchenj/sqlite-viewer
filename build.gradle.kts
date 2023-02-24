plugins {
    application
}

application {
    mainClass.set("de.cofinpro.sqliteviewer.ApplicationRunner")
}

repositories {
    mavenCentral()
}

group = "de.cofinpro"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
    implementation("org.xerial:sqlite-jdbc:3.41.0.0")

    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.1.1")
    testImplementation("org.mockito:mockito-inline:5.1.1") // needed ?

    testCompileOnly("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}