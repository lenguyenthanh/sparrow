package se.thanh.sparrow

import arrow.endpoint.spring.server.routerFunction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import se.thanh.sparrow.api.endpoints
import org.springframework.web.reactive.function.server.ServerResponse as SpringResponse

@Configuration
public class AppConfiguration {
  @Bean
  public fun usersRoute(): RouterFunction<SpringResponse> = routerFunction(
    endpoints()
  )

  // @Bean
  // fun securityWebFilterChain(
  //   http: ServerHttpSecurity
  // ): SecurityWebFilterChain? {
  //   return http.authorizeExchange()
  //     .pathMatchers("/actuator/**").permitAll()
  //     .anyExchange().authenticated()
  //     .and().build()
  // }
}
