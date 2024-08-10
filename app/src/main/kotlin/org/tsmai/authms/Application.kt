package org.tsmai.authms

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.web.reactive.config.EnableWebFlux
import org.tsmai.authms.Application.Companion.logger

@SpringBootApplication(scanBasePackages = ["org.tsmai.authms"])
@EnableWebFlux
@PropertySources(
    PropertySource(
        value = arrayOf(
            "classpath:tsmai-authms-application.yaml",
            "classpath:tsmai-authms-persistence-application.yaml"
        ), factory = YamlPropertySourceFactory::class
    )
)
class Application {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Application::class.java)
    }
}

fun main(args: Array<String>) {
    logger.info("Starting application")
    runApplication<Application>(*args)
    logger.info("Finished Starting application")
}