package org.tsmai.authms.services.registration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.tsmai.authms.mappers.resources.UserMapper
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.models.resource.User
import org.tsmai.authms.persistence.repositories.user.UserRepository
import org.tsmai.authms.services.authentication.interfaces.IJwtAuthService
import org.tsmai.authms.services.encryption.IEncryptionService
import org.tsmai.authms.services.exceptions.UserExistsError

@Service
class RegistrationService(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val userMapper: UserMapper,
    @Autowired
    private val encryptionService: IEncryptionService,
    @Autowired
    private val jwtAuthService: IJwtAuthService
) : IRegistrationService {

    companion object {
        val log: Logger = LoggerFactory.getLogger(RegistrationService::class.java)
    }

    override fun registerUser(user: User): String {
        userRepository.existsByEmail(email = user.email).let {
            if (it) {
                throw UserExistsError()
            }
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