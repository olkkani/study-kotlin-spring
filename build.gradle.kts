import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5" apply false
	id("io.spring.dependency-management") version "1.1.0"
	id("io.freefair.lombok") version "6.5.1"

	kotlin("jvm") version "1.7.21"
	kotlin("plugin.spring") version "1.7.21"
	kotlin("plugin.jpa") version  "1.7.21"
	// todo kapt 추가시 빌드에러 추후 수정 예정
//	kotlin("kapt") version "1.7.21"
}


java.sourceCompatibility = JavaVersion.VERSION_17

allprojects{
	group = "io.olkkani"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects{
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "io.spring.dependency-management")

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


	dependencies {
		// kotlin 필수 의존성
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		// getter setter 등을 kotlin 스럽게 생성자를 사용할 수 있다
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		// spring boot
		implementation("org.springframework.boot:spring-boot-starter-web")
//		developmentOnly("org.springframework.boot:spring-boot-devtools")
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
		testImplementation("com.ninja-squad:springmockk:4.0.0")
		// common
		implementation("org.apache.commons:commons-text:1.10.0")
		implementation("org.apache.commons:commons-lang3:3.12.0")
		implementation("commons-io:commons-io:2.11.0")
		// database
		implementation("org.postgresql:postgresql:42.5.1")
		runtimeOnly("com.h2database:h2")
		// persistence
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("com.querydsl:querydsl-jpa:5.0.0")
		// todo: kapt 추가시 빌드 에러, 추후 수정
//		kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
		// jwp 인증
		implementation("com.auth0:java-jwt:3.19.2")
		// other
		implementation("commons-fileupload:commons-fileupload:1.5")
	}
	// 멀티 모듈에서도 의존성을 제대로 가져오기 위한 설정
	dependencyManagement{
		imports{
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

