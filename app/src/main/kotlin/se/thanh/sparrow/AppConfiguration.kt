package se.thanh.sparrow

import arrow.endpoint.spring.server.routerFunction
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.web.reactive.function.server.RouterFunction
import se.thanh.sparrow.api.endpoints
import org.springframework.web.reactive.function.server.ServerResponse as SpringResponse

@Configuration
@EnableR2dbcRepositories
class AppConfiguration {
  @Bean
  fun usersRoute(): RouterFunction<SpringResponse> = routerFunction(
    endpoints()
  )

  @Bean
  fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
    val initializer = ConnectionFactoryInitializer()
    initializer.setConnectionFactory(connectionFactory)
    return initializer
  }
}
