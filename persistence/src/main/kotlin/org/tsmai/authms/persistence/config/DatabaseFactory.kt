package org.tsmai.authms.persistence.config

import kotlinx.serialization.json.JsonObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Locale

@Configuration
open class DatabaseFactory {

    companion object {
        private val log = LoggerFactory.getLogger(DatabaseFactory::class.java)
        private const val MONGODB = "mongodb"
        private const val COUCHBASE = "couchbase"
    }

    @Value("\${database.type}")
    private lateinit var databaseType: String

    private lateinit var databaseConfigMap: Map<String, IDatabaseConfig>

    @Bean
    open fun getDatabaseConfig(databaseConfigs: List<IDatabaseConfig>): IDatabaseConfig {
        log.debug("Attempting to create database using: $databaseType")
        databaseConfigMap = databaseConfigs.associateBy(IDatabaseConfig::getDatabaseType)
        log.debug("The following database has been identified: {}", databaseConfigMap.keys )
        return databaseConfigMap.getOrDefault(databaseType, CouchbaseConfig())
    }

}