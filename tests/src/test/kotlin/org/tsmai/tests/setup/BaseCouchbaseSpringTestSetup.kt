package org.tsmai.tests.setup

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.slf4j.LoggerFactory
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.couchbase.BucketDefinition
import org.testcontainers.couchbase.CouchbaseContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.time.Duration

@Testcontainers(disabledWithoutDocker = true)
internal class BaseCouchbaseSpringTestSetup : SpringTestSetup() {

    companion object {

        private val log = LoggerFactory.getLogger(BaseCouchbaseSpringTestSetup::class.java)
        private val COUCHBASE_IMAGE_NAME = "couchbase"
        private val DEFAULT_IMAGE_NAME = "couchbase/server"
        private val BUCKET_NAME = "auth"
        private val DEFAULT_IMAGE =
            DockerImageName.parse(COUCHBASE_IMAGE_NAME).asCompatibleSubstituteFor(DEFAULT_IMAGE_NAME)


        val couchbaseContainer = CouchbaseContainer(DEFAULT_IMAGE)
            .withCredentials("Administrator", "password")
            .withBucket(BucketDefinition(BUCKET_NAME).withPrimaryIndex(false))
            .withStartupTimeout(Duration.ofSeconds(60))

        @JvmStatic
        @DynamicPropertySource
        fun bindCouchbaseProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.couchbase.connection-string") { couchbaseContainer.connectionString }
            registry.add("spring.couchbase.username") { couchbaseContainer.username }
            registry.add("spring.couchbase.password") { couchbaseContainer.password }
            registry.add("spring.data.couchbase.bucket-name") { BUCKET_NAME }
        }

        @JvmStatic
        @BeforeAll
        fun setup() {
            runBlocking {
                couchbaseContainer.start()
                userToCouchBase("name=user", "/pools/default/buckets/auth/scopes")
                userToCouchBase("name=users", "/pools/default/buckets/auth/scopes/user/collections")
//                waitUntilDatastoreReadyOrTimedOut()
            }
            log.debug("The readiness check of datasource is finished")
        }

        @JvmStatic
        @AfterAll
        fun teardown() {
            couchbaseContainer.stop()
        }

        private fun userToCouchBase(body: String, resourcePath: String): ResponseEntity<String> {
            val httpEntity = HttpEntity(
                body,
                HttpHeaders().also {
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.accept = listOf(MediaType.APPLICATION_JSON)
                }
            )

            return TestRestTemplate()
                .withBasicAuth(couchbaseContainer.username, couchbaseContainer.password)
                .postForEntity(
                    "http://${couchbaseContainer.host}:${couchbaseContainer.bootstrapHttpDirectPort}$resourcePath",
                    httpEntity,
                    String::class.java
                )
        }

        private suspend fun waitUntilDatastoreReadyOrTimedOut() {
            var tryCount = 0
            do {
                tryCount++
                log.debug("The readiness check - retrying with $tryCount second(s) delay")
                val isPostCounterReady = try {
                    delay(tryCount * 1_000L)
                    callAndCheckIfAuthBucketReady()
                } catch (e: Exception) {
                    false
                }
            } while (!isPostCounterReady && tryCount <= 15)
        }

        private fun callAndCheckIfAuthBucketReady() =
            userToCouchBase(
                "statement=GET * from auth.user.users ",
                "/_p/query/query/service"
            )
                .let {
                    it.statusCode == HttpStatus.OK
                }
    }
}