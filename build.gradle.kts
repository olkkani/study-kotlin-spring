import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.1.0"
	id("io.freefair.lombok") version "6.5.1"

	kotlin("jvm") version "1.7.21"
	kotlin("plugin.spring") version "1.7.21"
	kotlin("plugin.jpa") version  "1.7.21"
	kotlin("kapt") version "1.7.21"
}

group = "io.olkkani"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	all {
		// spring boot 기본 logger 제외
		exclude(module = "spring-boot-starter-logging")
		// was tomcat 제외
		exclude(module = "spring-boot-starter-tomcat")
	}
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	// spring boot
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	// was
	implementation("org.springframework.boot:spring-boot-starter-undertow")
	// logging
	implementation("io.github.microutils:kotlin-logging:3.0.4")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.1")
	// view template
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// common
	implementation("org.apache.commons:commons-text:1.10.0")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("commons-io:commons-io:2.11.0")
	// database
	implementation("org.postgresql:postgresql:42.5.1")
	// persistence
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.querydsl:querydsl-jpa:5.0.0")
	kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
	// other
	implementation("commons-fileupload:commons-fileupload:1.4")
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