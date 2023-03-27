import org.jetbrains.kotlin.gradle.tasks.KotlinCompile



plugins {
	id("org.springframework.boot") version "2.7.5" apply false
	id("io.spring.dependency-management") version "1.1.0"

	id("org.jetbrains.kotlin.jvm") version "1.6.10"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.6.10"
	id("org.jetbrains.kotlin.plugin.spring") version "1.6.10"
	id("org.jetbrains.kotlin.kapt") version "1.6.10"
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
	group = "io.olkkani"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin-kapt")

	dependencies {
		// JWT 인증
		implementation("com.auth0:java-jwt:3.19.2")

		// Kotlin 로깅
		implementation("io.github.microutils:kotlin-logging:1.12.5")

		// Kotlin
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

		// common
		implementation("org.apache.commons:commons-text:1.10.0")
		implementation("org.apache.commons:commons-lang3:3.12.0")
		implementation("commons-io:commons-io:2.11.0")

		// H2DB
		runtimeOnly("com.h2database:h2")

		// Test
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("com.ninja-squad:springmockk:4.0.0")

		kapt("org.springframework.boot:spring-boot-configuration-processor")
	}
	// 멀티 모듈에서도 의존성을 제대로 가져오기 위한 설정
	dependencyManagement {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
		}
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
