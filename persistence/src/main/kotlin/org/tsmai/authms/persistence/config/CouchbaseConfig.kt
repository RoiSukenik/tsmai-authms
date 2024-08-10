package org.tsmai.authms.persistence.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories

@Configuration
@EnableReactiveCouchbaseRepositories
class CouchbaseConfig(
    @Value("\${spring.couchbase.connection-string}")
    val couchbaseConnectionString: String,
    @Value("\${spring.couchbase.username}")
    val couchbaseUsername: String,
    @Value("\${spring.couchbase.password}")
    val couchbasePassword: String,
    @Value("\${spring.data.couchbase.bucket-name}")
    val couchbaseBucketName: String
) : AbstractCouchbaseConfiguration(), IDatabaseConfig {

    companion object {
        val log: Logger = LoggerFactory.getLogger(CouchbaseConfig::class.java)
    }


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

//    override fun configureRepositoryOperationsMapping(baseMapping: RepositoryOperationsMapping) {
//        try {
//            log.info("user template")
//            val userTemplate: CouchbaseTemplate =
//                myCouchbaseTemplate(myCouchbaseClientFactory("users"), MappingCouchbaseConverter())
//            baseMapping.mapEntity(UserDTO::class.java, userTemplate)
//        } catch (e: Exception) {
//            log.debug(e.message)
//        }
//    }
//
//    private fun myCouchbaseTemplate(
//        couchbaseClientFactory: CouchbaseClientFactory,
//        mappingCouchbaseConverter: MappingCouchbaseConverter
//    ): CouchbaseTemplate {
//        return CouchbaseTemplate(couchbaseClientFactory, mappingCouchbaseConverter)
//    }
//
//    private fun myCouchbaseClientFactory(bucketNameToSaveTo: String): CouchbaseClientFactory {
//        return SimpleCouchbaseClientFactory(connectionString, authenticator(), bucketNameToSaveTo)
//    }

}