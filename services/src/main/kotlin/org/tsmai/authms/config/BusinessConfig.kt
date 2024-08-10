package org.tsmai.authms.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.tsmai.authms.services.authentication.interfaces.IJwtAuthService
import org.tsmai.authms.services.authentication.jwt.JwtAuthService
import org.tsmai.authms.services.encryption.EncryptionService
import org.tsmai.authms.services.encryption.IEncryptionService
import org.tsmai.authms.services.login.ILoginService
import org.tsmai.authms.services.login.LoginService
import org.tsmai.authms.services.registration.IRegistrationService
import org.tsmai.authms.services.registration.RegistrationService

@Configuration
class BusinessConfig {

    @Bean
    fun registrationService(): IRegistrationService {
        return RegistrationService()
    }

    @Bean
    fun loginService(): ILoginService {
        return LoginService()
    }

    @Bean
    fun encryptionService(): IEncryptionService {
        return EncryptionService()
    }

    @Bean
    fun jwtAuthService(): IJwtAuthService {
        return JwtAuthService()
    }
}