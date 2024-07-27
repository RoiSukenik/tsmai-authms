package org.tsmai.authms.services.authentication.jwt

import jakarta.inject.Inject
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.tsmai.authms.persistence.repositories.user.UserRepository

class CustomUserDetailsService: UserDetailsService {

    @Inject
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
         return userRepository.findByEmail(username).orElseThrow {
             UsernameNotFoundException("User not found")
         }
    }
}