package org.tsmai.tests.repositories

import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.testcontainers.shaded.org.awaitility.Awaitility.await
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.repositories.user.UserRepository
import org.tsmai.tests.setup.BaseCouchbaseSpringTestSetup
import org.tsmai.tests.setup.CouchbaseTestMocks.Companion.userDto1
import org.tsmai.tests.setup.CouchbaseTestMocks.Companion.userDto2
import reactor.test.StepVerifier
import java.util.concurrent.TimeUnit

internal class UserRepositoryTests : BaseCouchbaseSpringTestSetup() {

    @Inject
    private lateinit var userRepository: UserRepository

    @AfterEach
    fun cleanup(): Unit = runBlocking {
        userRepository.deleteAll()
    }

    @Test
    fun `Given no users saved in couchbase,When looking for user, Then return empty list`(): Unit = runBlocking {
        //act
        val response = userRepository.findAll()
        //assert
        StepVerifier.create(response)
            .assertNext {
                assertNull(it)
            }
            .expectComplete()
    }

    @Test
    fun `Given no users saved in couchbase,When saving a userDTO, Then return one user`(): Unit = runBlocking {
        //arrange
        userRepository.save(userDto1)
        //act
        await().pollInterval(1, TimeUnit.SECONDS).atMost(5, TimeUnit.SECONDS).untilAsserted {
            StepVerifier.create(userRepository.findById(userDto1.id.toString()))
                .assertNext {
                    assertNotNull(it)
                    assertEquals(userDto1.id.toString(), it.id.value)
                    assertEquals(userDto1.firstname, it.firstname)
                    assertEquals(userDto1.password, it.password)
                }.expectComplete()
        }

    }

    @Test
    fun `Given no users saved in couchbase,When saving 2 users, Then return a 2 user list`() = runBlocking {
        //arrange
        val userDTOList: List<UserDTO> = listOf(userDto1, userDto2)
        runBlocking {
            userRepository.saveAll(userDTOList)
        }
        //act
        await().pollInterval(1, TimeUnit.SECONDS).atMost(5, TimeUnit.SECONDS).untilAsserted {
            StepVerifier.create(userRepository.findAll())
                .assertNext {
                    assertNotNull(it)
                    assertEquals(userDto1.id.toString(), it.id.value)
                    assertEquals(userDto1.firstname, it.firstname)
                    assertEquals(userDto1.password, it.password)
                }
                .assertNext {
                    assertNotNull(it)
                    assertEquals(userDto2.id.toString(), it.id.value)
                    assertEquals(userDto2.firstname, it.firstname)
                    assertEquals(userDto2.password, it.password)
                }
                .expectComplete()
        }
    }

    @Test
    fun `Given one user in couchbase, When calling delete, Then user deleted`() = runBlocking {
        //arrange
        userRepository.save(userDto1)
        //act
        userRepository.deleteById(userDto1.id.toString())
        //assert
        await().pollInterval(1, TimeUnit.SECONDS).atMost(5, TimeUnit.SECONDS).untilAsserted {
            StepVerifier.create(userRepository.findById(userDto1.id.toString()))
                .expectError()
        }
    }
}