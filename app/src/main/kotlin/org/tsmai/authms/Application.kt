package org.tsmai.authms

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories
import org.tsmai.authms.Application.Companion.logger
import org.tsmai.authms.persistence.repositories.user.UserRepository


@SpringBootApplication(scanBasePackages = ["org.tsmai.authms"])
@EnableCouchbaseRepositories(basePackageClasses = [UserRepository::class])
class Application{

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Application::class.java)
    }
}

fun main(args: Array<String>) {
    logger.info("Starting application")
    runApplication<Application>(*args)
    logger.info("Finished Starting application")
}