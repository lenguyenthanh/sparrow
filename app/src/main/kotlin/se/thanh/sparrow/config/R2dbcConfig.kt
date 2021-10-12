package se.thanh.sparrow.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

// @Configuration
// class R2dbcConfig : AbstractR2dbcConfiguration() {
//   @Bean
//   override fun connectionFactory(): ConnectionFactory {
//     println("tetst connection")
//     return ConnectionFactories.get("r2dbc:tc:postgresql://localhost:5431/test?TC_IMAGE_TAG=14.0-alpine3.14")
//   }
// }
