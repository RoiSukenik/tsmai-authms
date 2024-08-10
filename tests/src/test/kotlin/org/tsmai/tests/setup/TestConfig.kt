package org.tsmai.tests.setup

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.tsmai.authms.YamlPropertySourceFactory

@Configuration
@PropertySources(
    PropertySource(
        value = arrayOf(
            "classpath:tsmai-authms-application-test.yaml",
            "classpath:tsmai-authms-application.yaml",
            "classpath:tsmai-authms-persistence-application.yaml"
        ), factory = YamlPropertySourceFactory::class
    )
)
class TestConfig {
}