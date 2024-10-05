package org.tsmai.authms.services.authentication.interfaces

fun interface IAuthService {

    fun getAuthType(): String
}

enum class AuthServiceType(s: String) {
    JWT("jwt"), AUTH0("auth0"), DB("db")
}