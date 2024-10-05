package org.tsmai.authms.persistence.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories

@Configuration
@EnableReactiveCouchbaseRepositories
class CouchbaseConfig : AbstractCouchbaseConfiguration(), IDatabaseConfig {

    companion object {
        private const val COUCHBASE = "couchbase"
        val log: Logger = LoggerFactory.getLogger(CouchbaseConfig::class.java)
    }

    @Value("\${spring.couchbase.connection-string}")
    private lateinit var couchbaseConnectionString: String

    @Value("\${spring.couchbase.username}")
    private lateinit var couchbaseUsername: String

    @Value("\${spring.couchbase.password}")
    private lateinit var couchbasePassword: String

    @Value("\${spring.data.couchbase.bucket-name}")
    private lateinit var couchbaseBucketName: String

    override fun getConnectionString(): String {
        return couchbaseConnectionString
    }

    override fun getUserName(): String {
        return couchbaseUsername
    }

    override fun getPassword(): String {
        return couchbasePassword
    }

    override fun getBucketName(): String {
        return couchbaseBucketName
    }

    override fun getDatabaseType(): String {
        return COUCHBASE
    }

}