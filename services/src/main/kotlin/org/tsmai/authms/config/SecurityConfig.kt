package org.tsmai.authms.config

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.services.authentication.jwt.JWTAuthenticationManager
import org.tsmai.authms.services.authentication.jwt.ReactiveCustomUserDetailsService
import org.tsmai.authms.services.utils.JwtUtil

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun reactiveCustomUserDetailsService(): ReactiveUserDetailsService {
        return ReactiveCustomUserDetailsService()
    }

    @Bean
    fun authenticationManager(): ReactiveAuthenticationManager {
        return JWTAuthenticationManager()
    }

    @Bean
    fun jwtUtil(): JwtUtil {
        return JwtUtil()
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.authorizeExchange { exchanges ->
            exchanges
                .pathMatchers("actuator/*").permitAll()
                .pathMatchers("/admin").hasAuthority(UserDTO.Roles.ADMIN.name)
                .pathMatchers("/api/v1/login", "/api/v1/logout").permitAll()
                .anyExchange().authenticated()
        }
            .csrf {
                it.disable()
            }
            .authenticationManager(authenticationManager())


        return http.build();
    }
}