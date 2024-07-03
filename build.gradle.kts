import java.util.Properties
// jhipster-needle-gradle-imports

plugins {
  java
  jacoco
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.git.properties)
  alias(libs.plugins.sonarqube)
  // jhipster-needle-gradle-plugins
}

val npmVersion by extra("10.8.1")
val nodeVersion by extra("v20.15.0")
// jhipster-needle-gradle-properties

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

jacoco {
  toolVersion = libs.versions.jacoco.get()
}

tasks.jacocoTestReport {
  dependsOn("test", "integrationTest")
  reports {
    xml.required.set(true)
    html.required.set(true)
  }
  executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
}

tasks.jacocoTestCoverageVerification {
  dependsOn("jacocoTestReport")
  violationRules {

      rule {
          element = "CLASS"

          limit {
              counter = "LINE"
              value = "COVEREDRATIO"
              minimum = "1.00".toBigDecimal()
          }

          limit {
              counter = "BRANCH"
              value = "COVEREDRATIO"
              minimum = "1.00".toBigDecimal()
          }
      }
  }
  executionData.setFrom(fileTree(layout.buildDirectory).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
}


defaultTasks("bootRun")

springBoot {
  mainClass = "tech.jhipster.sampleapp.SampleappApp"
}


gitProperties {
  failOnNoGitDirectory = false
  keys = listOf("git.branch", "git.commit.id.abbrev", "git.commit.id.describe", "git.build.version")
}


val sonarProperties = Properties()
File("sonar-project.properties").inputStream().use { inputStream ->
    sonarProperties.load(inputStream)
}

sonarqube {
    properties {
      sonarProperties
        .map { it -> it.key as String to (it.value as String).split(",").map { it.trim() } }
        .forEach { (key, values) -> property(key, values) }
      property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
      property("sonar.junit.reportPaths", "build/test-results/test,build/test-results/integrationTest")
    }
}

// jhipster-needle-gradle-plugins-configurations

repositories {
  mavenCentral()
  // jhipster-needle-gradle-repositories
}

group = "tech.jhipster.sampleapp"
version = "0.0.1-SNAPSHOT"

val profiles = (project.findProperty("profiles") as String? ?: "")
  .split(",")
  .map { it.trim() }
  .filter { it.isNotEmpty() }
// jhipster-needle-profile-activation

dependencies {
  implementation(libs.commons.lang3)
  implementation(platform(libs.spring.boot.dependencies))
  implementation(libs.spring.boot.starter)
  implementation(libs.spring.boot.configuration.processor)
  implementation(libs.spring.boot.starter.validation)
  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.spring.boot.starter.data.jpa)
  implementation(libs.hikariCP)
  implementation(libs.hibernate.core)
  implementation(libs.liquibase.core)
  implementation(libs.spring.boot.starter.security)
  implementation(libs.spring.boot.starter.oauth2.client)
  implementation(libs.spring.boot.starter.oauth2.resource.server)
  implementation(libs.springdoc.openapi.starter.webmvc.ui)
  implementation(libs.springdoc.openapi.starter.webmvc.api)
  // jhipster-needle-gradle-implementation-dependencies
  // jhipster-needle-gradle-compile-dependencies
  runtimeOnly(libs.spring.boot.devtools)
  runtimeOnly(libs.postgresql)
  // jhipster-needle-gradle-runtime-dependencies
  testImplementation(libs.spring.boot.starter.test)
  testImplementation(libs.reflections)
  testImplementation(libs.archunit.junit5.api)
  testImplementation(libs.testcontainers.postgresql)
  testImplementation(libs.spring.security.test)
  testImplementation(libs.cucumber.junit.platform.engine)
  testImplementation(libs.cucumber.java)
  testImplementation(libs.cucumber.spring)
  testImplementation(libs.junit.platform.suite)
  testImplementation(libs.jjwt.api)
  testImplementation(libs.jjwt.impl)
  testImplementation(libs.jjwt.jackson)

  // jhipster-needle-gradle-test-dependencies
}

// jhipster-needle-gradle-free-configuration-blocks

tasks.test {
  filter {
    includeTestsMatching("**Test*")
    excludeTestsMatching("**IT*")
    excludeTestsMatching("**CucumberTest*")
  }
  useJUnitPlatform()
  finalizedBy("jacocoTestCoverageVerification")
  // jhipster-needle-gradle-tasks-test
}

val integrationTest = task<Test>("integrationTest") {
  description = "Runs integration tests."
  group = "verification"
  shouldRunAfter("test")
  filter {
    includeTestsMatching("**IT*")
    includeTestsMatching("**CucumberTest*")
  }
  useJUnitPlatform()
}
