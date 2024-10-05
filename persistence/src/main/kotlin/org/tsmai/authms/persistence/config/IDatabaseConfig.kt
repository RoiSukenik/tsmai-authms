package org.tsmai.authms.persistence.config

fun interface IDatabaseConfig {
    fun getDatabaseType():String
}