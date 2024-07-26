package org.tsmai.authms.services.encryption

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class EncryptionService : IEncryptionService {

    @Autowired
    private lateinit var encoder: PasswordEncoder

    override fun encrypt(password: String): String {
        val encryptedPassword = encoder.encode(password)
        return encryptedPassword
    }

    override fun decrypt(password: String, encryptedPassword: String): Boolean {
        return encoder.matches(password, encryptedPassword)
    }
}