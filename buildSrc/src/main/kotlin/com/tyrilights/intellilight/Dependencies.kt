object Version {
  const val kotlin: String = "1.5.31"
  const val arrow: String = "1.0.0"
  const val arrowEndpoint: String = "6f337d82e1"

  const val kotlinx: String = "1.5.2"
  const val reactorKotlinExtensions: String = "1.1.3"

  const val kotlinxSerializationJson: String = "1.3.0"
  const val ktlint: String = "10.1.0"
  const val springBoot: String = "2.5.5"
  const val springDependency: String = "1.0.11.RELEASE"

  const val kotest: String = "5.0.0-M2"
  const val mockk: String = "1.12.0"
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
  const val springDataR2dbc: String = "org.springframework.boot:spring-boot-starter-data-r2dbc:${Version.springBoot}"
  const val springBootStarterWeb: String = "org.springframework.boot:spring-boot-starter-web:${Version.springBoot}"
  const val springBootStarterWebflux: String =
    "org.springframework.boot:spring-boot-starter-webflux:${Version.springBoot}"
  const val reactorKotlinExtensions: String =
    "io.projectreactor.kotlin:reactor-kotlin-extensions:${Version.reactorKotlinExtensions}"

  const val kotestRunner: String = "io.kotest:kotest-runner-junit5:${Version.kotest}"
  const val kotestAssertions: String = "io.kotest:kotest-assertions-core:${Version.kotest}"
  const val kotestProperty: String = "io.kotest:kotest-property:${Version.kotest}"
  const val mockk: String = "io.mockk:mockk:${Version.mockk}"
}

object Plugins {
  const val kotlinSerialization: String = "org.jetbrains.kotlin.plugin.serialization"
  const val ktlint: String = "org.jlleitschuh.gradle.ktlint"
  const val kotlinSpring: String = "plugin.spring"
  const val springBoot: String = "org.springframework.boot"
  const val springDependency: String = "io.spring.dependency-management"
}
