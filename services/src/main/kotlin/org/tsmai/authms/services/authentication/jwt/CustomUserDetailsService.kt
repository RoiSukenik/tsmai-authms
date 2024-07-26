package org.tsmai.authms.services.authentication.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.tsmai.authms.persistence.repositories.user.UserRepository

@Service
class CustomUserDetailsService(@Autowired private val userRepository: UserRepository): UserDetailsService {



    override fun loadUserByUsername(username: String): UserDetails {
         return userRepository.findByEmail(username).orElseThrow {
             UsernameNotFoundException("User not found")
         }
    }
}