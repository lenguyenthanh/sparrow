plugins {
  id(Plugins.springBoot) version Version.springBoot
  id(Plugins.springDependency) version Version.springDependency
  kotlin(Plugins.kotlinSpring) version Version.kotlin
  id("application")
}

dependencies {
  implementation(project(Libs.api))
  implementation(Libs.kotlinReflect)

  implementation(Libs.kotlinxCoroutinesReactive)
  implementation(Libs.kotlinxCoroutinesReactor)
  implementation(Libs.reactorKotlinExtensions)


  implementation(Libs.arrowCore)
  implementation(Libs.arrowFx)
  implementation(Libs.arrowEndpointCore)
  implementation(Libs.arrowEndpointSpringServer)

  implementation(Libs.springBootStarterWebflux)
  implementation(Libs.springActuator)

  implementation(Libs.springDataR2dbc)
  implementation(Libs.r2dbcPostgres)
  // implementation(Libs.r2dbcPool)
  implementation(Libs.postgres)
  implementation(Libs.flyway)

  testImplementation(Libs.springBootTest)
  // testImplementation(Libs.kotestRunner)
  // testImplementation(Libs.kotestAssertions)
  // testImplementation(Libs.kotestProperty)
  // testImplementation(Libs.kotestSpring)
  testImplementation(Libs.reactorTest)
  testImplementation(Libs.mockk)
  testImplementation(Libs.testContainer)


  runtimeOnly("io.r2dbc:r2dbc-postgresql")
  runtimeOnly("org.postgresql:postgresql")

}
