package se.thanh.sparrow.user

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

// data models
@Table("users")
data class User(
  @Id
  val id: Long? = null,
  val name: String,
  val login: String,
  val email: String,
)

// rest models
public data class ErrorMessage(val message: String)
