package org.tsmai.authms.services.authentication.jwt

import jakarta.inject.Inject
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.repositories.user.UserRepository
import reactor.core.publisher.Mono
import java.util.Optional

class ReactiveCustomUserDetailsService : ReactiveUserDetailsService {

    @Inject
    private lateinit var userRepository: UserRepository

    override fun findByUsername(username: String?): Mono<UserDetails> {
        return userRepository.findByEmail(username.orEmpty()).log().map(Optional<UserDTO>::get)
    }
}