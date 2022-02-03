object Version {
  const val kotlin: String = "1.6.10"
  const val arrow: String = "1.0.1"
  const val arrowEndpoint: String = "6f337d82e1"

  const val kotlinx: String = "1.6.0"
  const val reactorKotlinExtensions: String = "1.1.3"

  const val kotlinxSerializationJson: String = "1.3.0"
  const val ktlint: String = "10.1.0"
  const val springBoot: String = "2.6.2"
  const val springDependency: String = "1.0.11.RELEASE"

  const val r2dbcPostgres = "0.8.10.RELEASE"
  const val r2dbcPool = "0.9.0.M2"
  const val postgres = "42.2.24"
  const val flyway = "8.0.0"

  const val kotest: String = "5.0.2"
  const val kotestSpring: String = "1.0.1"
  const val mockk: String = "1.12.0"
  const val testcontainers: String = "1.16.0"
  const val reactorTest: String = "3.4.10"

  const val kover = "0.5.0"
}

object Libs {
  const val api: String = ":api"

  const val kotlinStdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
  const val kotlinReflect: String = "org.jetbrains.kotlin:kotlin-reflect:${Version.kotlin}"

  const val kotlinxCoroutines: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinx}"
  const val kotlinxCoroutinesReactor: String = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Version.kotlinx}"
  const val kotlinxCoroutinesReactive: String = "org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Version.kotlinx}"

  const val arrowCore: String = "io.arrow-kt:arrow-core:${Version.arrow}"
  const val arrowFx: String = "io.arrow-kt:arrow-fx-coroutines:${Version.arrow}"

  const val arrowEndpointCore: String = "com.github.lenguyenthanh.Arrow-Endpoint:core:${Version.arrowEndpoint}"
  const val arrowEndpointSpringServer: String =
    "com.github.lenguyenthanh.Arrow-Endpoint:spring-web-server:${Version.arrowEndpoint}"

  const val kotlinxSerializationJson: String =
    "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinxSerializationJson}"

  const val springActuator: String = "org.springframework.boot:spring-boot-starter-actuator:${Version.springBoot}"
  const val springBootStarterWeb: String = "org.springframework.boot:spring-boot-starter-web:${Version.springBoot}"
  const val springBootStarterWebflux: String =
    "org.springframework.boot:spring-boot-starter-webflux:${Version.springBoot}"
  const val reactorKotlinExtensions: String =
    "io.projectreactor.kotlin:reactor-kotlin-extensions:${Version.reactorKotlinExtensions}"

  const val springDataR2dbc: String = "org.springframework.boot:spring-boot-starter-data-r2dbc:${Version.springBoot}"
  const val r2dbcPool: String = "io.r2dbc:r2dbc-pool:${Version.r2dbcPool}"
  const val r2dbcPostgres: String = "io.r2dbc:r2dbc-postgresql:${Version.r2dbcPostgres}"
  const val postgres: String = "org.postgresql:postgresql:${Version.postgres}"
  const val flyway: String = "org.flywaydb:flyway-core:${Version.flyway}"

  const val kotestRunner: String = "io.kotest:kotest-runner-junit5:${Version.kotest}"
  const val kotestAssertions: String = "io.kotest:kotest-assertions-core:${Version.kotest}"
  const val kotestProperty: String = "io.kotest:kotest-property:${Version.kotest}"
  const val kotestSpring: String = "io.kotest.extensions:kotest-extensions-spring:${Version.kotestSpring}"

  const val springBootTest: String = "org.springframework.boot:spring-boot-starter-test:${Version.springBoot}"
  const val reactorTest: String = "io.projectreactor:reactor-test:${Version.reactorTest}"
  const val mockk: String = "io.mockk:mockk:${Version.mockk}"
  const val testContainer: String = "org.testcontainers:postgresql:${Version.testcontainers}"
}

object Plugins {
  const val kotlinSerialization: String = "org.jetbrains.kotlin.plugin.serialization"
  const val ktlint: String = "org.jlleitschuh.gradle.ktlint"
  const val kotlinSpring: String = "plugin.spring"
  const val springBoot: String = "org.springframework.boot"
  const val springDependency: String = "io.spring.dependency-management"
  const val kover: String = "org.jetbrains.kotlinx.kover"
}
