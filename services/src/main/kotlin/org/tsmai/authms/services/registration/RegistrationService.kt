package org.tsmai.authms.services.registration

import jakarta.inject.Inject
import lombok.NoArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.tsmai.authms.mappers.resources.UserMapper
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.models.resource.User
import org.tsmai.authms.persistence.repositories.user.UserRepository
import org.tsmai.authms.services.authentication.interfaces.IJwtAuthService
import org.tsmai.authms.services.encryption.IEncryptionService
import org.tsmai.authms.services.exceptions.UserExistsError

class RegistrationService : IRegistrationService {

    @Inject
    private lateinit var userRepository: UserRepository
    @Inject
    private lateinit var userMapper: UserMapper
    @Inject
    private lateinit var encryptionService: IEncryptionService
    @Inject
    private lateinit var jwtAuthService: IJwtAuthService

    companion object {
        val log: Logger = LoggerFactory.getLogger(RegistrationService::class.java)
    }

    override fun registerUser(user: User): String {
        userRepository.existsByEmail(email = user.email).log().let {
            it.handle<Unit> { user, sink -> if(user) sink.error(UserExistsError()) }
        }
        val userDTO: UserDTO = userMapper.toUserDTO(user).apply {
            log.debug("Encrypting password...")
            user.password = encryptionService.encrypt(user.password)
            log.debug("Password encrypted")
            this.roles?.add(UserDTO.Roles.BASIC_USER)
        }
        log.debug("Registering user")
        userRepository.save(userDTO)
        val jwtToken = jwtAuthService.generateToken(userDTO)
        log.debug("User registered")
        return jwtToken
    }


}