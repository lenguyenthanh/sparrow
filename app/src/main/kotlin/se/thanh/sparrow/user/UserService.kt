package se.thanh.sparrow.user

import org.springframework.stereotype.Service

@Service
public class UserService(private val repo: UserRepository) {

  fun findAll() = repo.findAll()
  suspend fun findById(id: Long) = repo.findById(id)
  suspend fun findByEmail(email: String) = repo.findByEmail(email)
  // suspend fun addOne(user: UserDTO) = repo.save(user.toModel()).awaitFirstOrNull()
  // suspend fun updateOne(id: Long, user: UserDTO): User? {
  //   val existingUser = findById(id)
  //   return if (existingUser != null) repo.save(user.toModel(withId = id)).awaitFirstOrNull() else null
  // }

  suspend fun deleteOne(id: Long): Boolean {
    val existingUser = findById(id)
    return if (existingUser != null) {
      repo.delete(existingUser)
      true
    } else false
  }
}
