package se.thanh.sparrow.user

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface UserRepository : ReactiveCrudRepository<User, Long> {

  @Query("SELECT u.* FROM users u WHERE u.email = :email")
  fun findByEmail(email: String): Flux<User>
}
