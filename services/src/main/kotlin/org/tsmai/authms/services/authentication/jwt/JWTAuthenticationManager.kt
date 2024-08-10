package org.tsmai.authms.services.authentication.jwt

import jakarta.inject.Inject
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.tsmai.authms.services.authentication.interfaces.IJwtAuthService
import reactor.core.publisher.Mono


class JWTAuthenticationManager : ReactiveAuthenticationManager {

    @Inject
    private lateinit var jwtAuthService: IJwtAuthService

    @Inject
    private lateinit var reactiveCustomUserDetailsService: ReactiveUserDetailsService

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        val token:String = authentication?.principal as String
        val username:String = jwtAuthService.extractUsername(token)

        return reactiveCustomUserDetailsService.findByUsername(username).handle<Authentication> { userDetails, sink ->
            if(jwtAuthService.isTokenValid(token, userDetails)){
                sink.next(authentication)
            }
            else{
                sink.error(AuthenticationServiceException("Token invalid"))
            }
        }
    }

    fun authenticationConverter(): ServerAuthenticationConverter {
        return ServerAuthenticationConverter { exchange ->
            var token = exchange.request.headers.getFirst("Authorization")
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7)
                return@ServerAuthenticationConverter Mono.just<Authentication>(SecurityContextHolder.getContext().authentication)
            }
            Mono.empty()
        }
    }
}