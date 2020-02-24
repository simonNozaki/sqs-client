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
    maven("https://maven.pkg.github.com/simonNozaki/api-queue-gateway-sdk") {
        name = "github"
        credentials {
            username = findProperty("GITHUB_USER").toString()
            password = findProperty("GITHUB_TOKEN").toString()
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.amazonaws:aws-java-sdk-sqs:1.11.722")
    implementation("com.example.apigateway:api-queue-gateway-sdk:1.0.0")
    testCompile("junit:junit:4.12")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform()
}