package org.tsmai.authms.persistence.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories
class MongodbConfig: AbstractMongoClientConfiguration() ,IDatabaseConfig {
    companion object{
        protected const val MONGO :String = "mongo"
    }
    override fun getDatabaseName(): String {
        TODO("Not yet implemented")
    }

    override fun getDatabaseType(): String {
        return MONGO
    }
}