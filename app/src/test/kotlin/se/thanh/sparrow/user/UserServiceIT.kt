package se.thanh.sparrow.user

import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.ResourceLoader
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import reactor.kotlin.core.publisher.toFlux
import se.thanh.sparrow.TestContainerInitializer
import java.util.stream.Stream

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DataR2dbcTest
@ContextConfiguration(initializers = [TestContainerInitializer::class])
@ActiveProfiles("test-containers")
internal class UserServiceIT(
  @Autowired private val repo: UserRepository
) {
  private lateinit var service: UserService

  @BeforeAll
  fun beforeAll() {
    initDatabase()
    service = UserService(repo)
  }

  @Test
  @Order(1)
  fun `findAll returns values`() {
    runBlocking {
      val resp = service.findAll()
      assertThat(resp.count()).isEqualTo(3)
    }
  }

  @Test
  fun `findById returns a value`() {
    runBlocking {
      //get user1 id
      val dbUser = service.findByEmail("user1@users.com").first()
      val resp = service.findById(dbUser.id!!)
      assertThat(resp).isNotNull
      assertThat(resp?.name).isEqualTo("User1")
    }
  }

  @Test
  fun `findById returns a null if value does not exists`() {
    runBlocking {
      val resp = service.findById(999)
      assertThat(resp).isNull()
    }
  }

  @Test
  fun `findByEmail returns a value`() {
    runBlocking {
      val resp = service.findByEmail("user2@users.com")
      assertThat(resp.count()).isEqualTo(1)
      resp.map {
        assertThat(it.name).isEqualTo("User2")
      }
    }
  }

  @Test
  fun `findByEmail returns a null if email does not exists`() {
    runBlocking {
      val resp = service.findByEmail("unknown@users.com")
      assertThat(resp.count()).isEqualTo(0)
    }
  }

  @Test
  fun `deleteOne deletes inexisting user return false`() {
    runBlocking {
      // add a new user
      val resp = service.deleteOne(9999)
      assertThat(resp).isFalse()
    }
  }

  private fun initDatabase() {
    repo.deleteAll().subscribe()

    val initData = Stream.of(
      User(null, "User1", "user01", "user1@users.com"),
      User(null, "User2", "user02", "user2@users.com"),
      User(null, "User3", "user03", "user3@users.com")
    )
    val saveAll = repo.saveAll(initData.toFlux())
    saveAll.subscribe()
  }
}
