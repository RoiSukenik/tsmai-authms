package org.tsmai.tests.setup

import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.tsmai.authms.Application

@ExtendWith(SpringExtension::class)
@EnableAutoConfiguration
@SpringBootTest(
    classes = [Application::class,TestConfig::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal open class SpringTestSetup: TestSetup() {
    companion object {
        private val log = LoggerFactory.getLogger(SpringTestSetup::class.java)
    }

}