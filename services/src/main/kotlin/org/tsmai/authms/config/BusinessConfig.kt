package org.tsmai.authms.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.tsmai.authms.services.encryption.EncryptionService
import org.tsmai.authms.services.encryption.IEncryptionService
import org.tsmai.authms.services.registration.IRegistrationService
import org.tsmai.authms.services.registration.RegistrationService
import org.tsmai.authms.services.utils.JwtUtil

@Configuration
class BusinessConfig {

    @Bean
    fun registrationService(): IRegistrationService {
        return RegistrationService()
    }

    @Bean
    fun encryptionService(): IEncryptionService {
        return EncryptionService()
    }

    @Bean
    fun jwtUtil(): JwtUtil {
        return JwtUtil()
    }
}