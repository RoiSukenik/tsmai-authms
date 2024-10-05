package org.tsmai.authms.services.login

import jakarta.inject.Inject
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.tsmai.authms.services.encryption.IEncryptionService
import org.tsmai.authms.services.utils.JwtUtil

class LoginService: ILoginService {

    @Inject private lateinit var jwtUtil: JwtUtil
    @Inject private lateinit var reactiveCustomUserDetailsService: ReactiveUserDetailsService
    @Inject private lateinit var encryptionService: IEncryptionService

    override fun login(username: String, password: String) {
        reactiveCustomUserDetailsService.findByUsername(username).log().handle<String> { userDTO, sink ->
            if(encryptionService.decrypt(password, userDTO.password)) {
                sink.next(jwtUtil.generateToken(userDTO))
                return@handle
            } else {
                sink.error(BadCredentialsException("Invalid username or password"))
            }
        }
    }
}