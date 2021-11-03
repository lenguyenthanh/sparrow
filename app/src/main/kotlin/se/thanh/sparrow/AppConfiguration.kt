package se.thanh.sparrow

import arrow.endpoint.spring.server.routerFunction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
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
}
