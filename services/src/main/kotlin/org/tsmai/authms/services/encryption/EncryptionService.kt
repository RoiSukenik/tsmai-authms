package org.tsmai.authms.services.encryption

import jakarta.inject.Inject
import org.springframework.security.crypto.password.PasswordEncoder

class EncryptionService : IEncryptionService {

    @Inject
    private lateinit var encoder: PasswordEncoder

    override fun encrypt(password: String): String {
        val encryptedPassword = encoder.encode(password)
        return encryptedPassword
    }

    override fun decrypt(password: String, encryptedPassword: String): Boolean {
        return encoder.matches(password, encryptedPassword)
    }
}