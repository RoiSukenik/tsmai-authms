package org.tsmai.authms.persistence.models.resource

data class User(
    val id: UserId,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    var password: String
)
