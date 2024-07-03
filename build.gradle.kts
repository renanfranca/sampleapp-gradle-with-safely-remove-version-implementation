// jhipster-needle-gradle-imports

plugins {
  java
  jacoco
  alias(libs.plugins.spring.boot)
  // jhipster-needle-gradle-plugins
}

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
  // jhipster-needle-gradle-implementation-dependencies
  // jhipster-needle-gradle-compile-dependencies
  // jhipster-needle-gradle-runtime-dependencies
  testImplementation(libs.spring.boot.starter.test)

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
