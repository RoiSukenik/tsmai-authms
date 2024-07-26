package org.tsmai.authms.services.encryption

interface IEncryptionService {
    fun encrypt(password: String): String
    fun decrypt(password: String, encryptedPassword: String): Boolean
}