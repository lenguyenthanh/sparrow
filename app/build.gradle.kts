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
}
