package org.tsmai.tests

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal class TestApplication {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TestApplication::class.java)
    }
    @Test
    fun contextLoads() {
        logger.info("Application Context Loads")
    }
}