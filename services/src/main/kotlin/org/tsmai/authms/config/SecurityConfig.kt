package org.tsmai.authms.config

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.tsmai.authms.filters.JwtAuthFilter
import org.tsmai.authms.services.authentication.jwt.CustomUserDetailsService
import org.tsmai.authms.services.utils.JwtUtil

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return CustomUserDetailsService()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService())
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun jwtAuthFilter(): JwtAuthFilter {
        return JwtAuthFilter()
    }

    @Bean
    fun jwtUtil(): JwtUtil {
        return JwtUtil()
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf ->
            csrf.disable()
        }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/").permitAll().anyRequest().authenticated()
            }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build();
    }
}