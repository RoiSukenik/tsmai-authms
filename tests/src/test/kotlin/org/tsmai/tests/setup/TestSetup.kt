package org.tsmai.tests.setup

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ExtendWith(MockitoExtension::class)
internal open class TestSetup {

    companion object {
        val log: Logger = LoggerFactory.getLogger(TestSetup::class.java)
    }
}