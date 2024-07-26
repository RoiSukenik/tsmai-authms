package org.tsmai.authms.services.authentication.interfaces

import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails

interface IJwtAuthService: IAuthService {
    fun extractUsername(jwt: String?): String
    fun <T> extractClaim(token: String?, claimsResolver: (Claims) -> T): T
    fun generateToken(userDetails: UserDetails): String
    fun generateToken(extraClaims: Map<String?, Any?>, userDetails: UserDetails): String
    fun getExpirationTime(): Long
    fun isTokenValid(token: String?, userDetails: UserDetails): Boolean
}