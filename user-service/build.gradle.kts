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
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("at.favre.lib:bcrypt:0.9.0")
    implementation("com.auth0:java-jwt:4.3.0")

    runtimeOnly("io.r2dbc:r2dbc-h2")
}




