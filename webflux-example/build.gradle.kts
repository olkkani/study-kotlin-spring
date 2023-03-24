//apply(plugin="kotlin-jpa")


configurations {
	all {
//         spring boot 기본 logger 제외
//		exclude(module = "spring-boot-starter-logging")
		// was tomcat 제외
//        exclude(module = "spring-boot-starter-tomcat")
	}
}


dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-undertow")
//	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
//    implementation("org.springframework.boot:spring-boot-devtools")
//	implementation("org.springframework.boot:spring-boot-starter-log4j2")

	// view template
//	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
//	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")

	// persistence
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("com.querydsl:querydsl-jpa:5.0.0")
//    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
//    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.1")



	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("io.projectreactor:reactor-test")
	implementation("org.springframework.data:spring-data-r2dbc")

}




