plugins {
  id("java")
  id("org.springframework.boot") version "3.5.4"
  id("io.spring.dependency-management") version "1.1.6"
}

group = "com.geomax"
version = "0.1.0"

java {
  toolchain { languageVersion = JavaLanguageVersion.of(21) }
}

repositories { mavenCentral() }

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
  implementation("org.flywaydb:flyway-core:10.17.0")
  implementation("org.flywaydb:flyway-database-postgresql:10.17.0")
  implementation("org.flywaydb:flyway-core:10.17.0")
  implementation("org.flywaydb:flyway-database-postgresql:10.17.0")
  implementation("org.flywaydb:flyway-core:10.17.0")
  implementation("org.flywaydb:flyway-database-postgresql:10.17.0")
  implementation("io.jsonwebtoken:jjwt-api:0.11.5")

  compileOnly("org.projectlombok:lombok:1.18.34")
  annotationProcessor("org.projectlombok:lombok:1.18.34")
  testCompileOnly("org.projectlombok:lombok:1.18.34")
  testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

  runtimeOnly("org.postgresql:postgresql:42.7.4")
  runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
  runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")


  testImplementation("com.h2database:h2:2.2.224")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
  testImplementation("org.assertj:assertj-core:3.26.3")
  testImplementation("org.testcontainers:junit-jupiter:1.20.2")
  testImplementation("org.testcontainers:postgresql:1.20.2")
  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.mockito:mockito-inline:5.2.0")

}

tasks.withType<Test> { useJUnitPlatform() }
