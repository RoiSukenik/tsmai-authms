package org.tsmai.authms.persistence.repositories.user

import org.springframework.stereotype.Repository
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.repositories.IBaseRepository
import java.util.Optional

@Repository
interface UserRepository : IBaseRepository<UserDTO, String> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<UserDTO>
}