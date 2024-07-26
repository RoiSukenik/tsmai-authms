package org.tsmai.tests.setup

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("application-test.yaml")
open class TestConfig {
}