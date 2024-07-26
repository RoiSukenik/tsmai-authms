package org.tsmai.tests.repositories

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.testcontainers.shaded.org.awaitility.Awaitility.await
import org.tsmai.tests.setup.BaseCouchbaseSpringTestSetup
import org.tsmai.tests.setup.CouchbaseTestMocks.Companion.userDto1
import org.tsmai.tests.setup.CouchbaseTestMocks.Companion.userDto2
import org.tsmai.authms.persistence.repositories.user.UserRepository
import java.util.concurrent.TimeUnit

internal class UserRepositoryTests : BaseCouchbaseSpringTestSetup() {

    @Autowired
    private lateinit var userRepository: UserRepository

    @AfterEach
    fun cleanup() = runBlocking {
        userRepository.deleteAll()
    }

    @Test
    fun `Given no users saved in couchbase,When looking for user, Then return empty list`() = runBlocking {
        //act
        val response = userRepository.findAll()
        //assert
        assertTrue(response.toList().isEmpty())
    }

    @Test
    fun `Given no users saved in couchbase,When saving a userDTO, Then return one user`() = runBlocking {
        //arrange
        val user = userRepository.save(userDto1)
        //act
        await().pollInterval(1, TimeUnit.SECONDS).atMost(10, TimeUnit.SECONDS).untilAsserted {
            userRepository.findById(userDto1.id.toString()).let {
                //assert
                assertNotNull(it)
                if (it.isPresent) {
                    assertEquals(userDto1.id.toString(), it.get().id.value)
                    assertEquals(userDto1.firstname, it.get().firstname)
                    assertEquals(userDto1.password, it.get().password)
                }
            }
        }

    }

    @Test
    fun `Given no users saved in couchbase,When saving 2 users, Then return a 2 user list`() = runBlocking {
        //arrange
        userRepository.saveAll(listOf(userDto1, userDto2))
        //act
        await().pollInterval(1, TimeUnit.SECONDS).atMost(30, TimeUnit.SECONDS).untilAsserted {
            userRepository.findAll().let {
                //assert
                assertNotNull(it)
                assertTrue(it.toList().size == 2)
                assertTrue(it.any { user -> user.id.value == userDto1.id.toString() }
                        && it.any { user -> user.id.value == userDto2.id.toString() })
            }
        }
    }

    @Test
    fun `Given one user in couchbase, When calling delete, Then user deleted`() = runBlocking {
        //arrange
        val user = userRepository.save(userDto1)
        //act
        userRepository.deleteById(userDto1.id.toString())
        //assert
        await().pollInterval(1, TimeUnit.SECONDS).atMost(10, TimeUnit.SECONDS).untilAsserted {
            assertFalse(userRepository.findById(userDto1.id.toString()).isPresent)
        }
    }
}