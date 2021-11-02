package se.thanh.sparrow.user

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<User, Long> {

  @Query("SELECT u.* FROM users u WHERE u.email = :email")
  suspend fun findByEmail(email: String): User?
}
