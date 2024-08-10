package org.tsmai.authms.persistence.repositories.user

import org.springframework.stereotype.Repository
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.repositories.IBaseRepository
import reactor.core.publisher.Mono
import java.util.Optional

@Repository
interface UserRepository : IBaseRepository<UserDTO, String> {
    fun existsByEmail(email: String): Mono<Boolean>
    fun findByEmail(email: String): Mono<Optional<UserDTO>>
}