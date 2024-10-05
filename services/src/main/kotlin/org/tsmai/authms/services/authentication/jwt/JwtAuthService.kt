package org.tsmai.authms.services.authentication.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.tsmai.authms.services.authentication.interfaces.AuthServiceType
import org.tsmai.authms.services.authentication.interfaces.IJwtAuthService
import java.util.Date
import javax.crypto.SecretKey

class JwtAuthService : IJwtAuthService {
    companion object {
        val AUTH_SERVICE_TYPE = AuthServiceType.JWT.name
    }

    override fun getAuthType(): String {
        return AUTH_SERVICE_TYPE
    }

    @Value("\${security.jwt.secret-key}")
    private val secretKey: String? = null

    @Value("\${security.jwt.expiration-time}")
    private val jwtExpiration: Long = 0

    override fun extractUsername(jwt: String?): String {
        return extractClaim(jwt, Claims::getSubject)
    }

    override fun <T> extractClaim(token: String?, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    override fun generateToken(userDetails: UserDetails): String {
        return generateToken(HashMap(), userDetails)
    }

    override fun generateToken(extraClaims: Map<String?, Any?>, userDetails: UserDetails): String {
        return buildToken(extraClaims, userDetails, jwtExpiration)
    }

    override fun getExpirationTime(): Long {
        return jwtExpiration
    }

    private fun buildToken(
        extraClaims: Map<String?, Any?>,
        userDetails: UserDetails,
        expiration: Long
    ): String {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey())
            .compact()
    }

    override fun isTokenValid(token: String?, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String?): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String?): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes: ByteArray = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

}