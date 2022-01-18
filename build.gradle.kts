import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl` apply true
  kotlin("jvm") version Version.kotlin apply false
  id(Plugins.kotlinSerialization) version Version.kotlin apply false
  id(Plugins.ktlint) version Version.ktlint apply true
  id(Plugins.kover) version Version.kover
}


allprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = Plugins.ktlint)
  apply(plugin = "org.gradle.idea")

  group = "se.thanh.sparrow"
  version = "0.0.1"

  repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
  }

  // Common dependencies
  dependencies {
    implementation(Libs.kotlinStdlib)
    implementation(Libs.arrowCore)
    implementation(Libs.arrowEndpointCore)
    implementation(Libs.kotlinxSerializationJson)
    // testImplementation(Libs.kotestRunner)
    // testImplementation(Libs.kotestAssertions)
    // testImplementation(Libs.kotestProperty)

  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "17"
      freeCompilerArgs = listOf("-Xjsr305=strict")
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

  ktlint {
    filter {
      exclude("build.gradle.kts") // TODO: fix doesnt inspect kts file with correct indent correctly
    }
  }

}
