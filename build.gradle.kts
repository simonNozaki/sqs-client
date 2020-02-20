import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
}

group = "com.example.sqs"
version  = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.amazonaws:aws-java-sdk-sqs:1.11.722")
    testCompile("junit:junit:4.12")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform()
}