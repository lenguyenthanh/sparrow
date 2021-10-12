package se.thanh.sparrow

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.MapPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.lifecycle.Startables
import java.util.stream.Stream

class TestContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

  override fun initialize(applicationContext: ConfigurableApplicationContext) {
    val container = TestPostgresContainer
    container
      .withExposedPorts(5432, 5431)
      .withPassword("test")
      .withUsername("test")

    container.start()

    val env = applicationContext.environment
    val conf = mapOf(
      "spring.r2dbc.url" to container.r2dbcUrl(),
      "spring.r2dbc.name" to container.username,
      "spring.r2dbc.password" to container.password,
      "spring.flyway.url" to container.jdbcUrl,
      "spring.flyway.user" to container.username,
      "spring.flyway.password" to container.password,
    )

    val testContainers = MapPropertySource("testcontainers", conf)

    env.propertySources.addFirst(testContainers)

    // applicationContext.refresh()
    // val f = applicationContext.getBean("flyway")
  }

  companion object {
    object TestPostgresContainer : PostgreSQLContainer<TestPostgresContainer>("postgres:14.0-alpine3.14") {

      fun r2dbcUrl(): String =
        "r2dbc:postgresql://$host:$firstMappedPort/$databaseName"

      fun startContainers() {
        Startables.deepStart(Stream.of(this)).join()
        // we can add further containers
        // here like rabbitmq or other databases
      }

      override fun stop() {
        //do nothing jvm handles it
      }
    }
  }
}

