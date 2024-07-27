package com.tsmai.controllers.authentication

import com.tsmai.controllers.models.AuthenticationRequest
import com.tsmai.controllers.models.AuthenticationResponse
import com.tsmai.controllers.models.RegistrationRequest
import jakarta.inject.Inject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.tsmai.authms.services.registration.IRegistrationService

@RequestMapping(path = ["/authentication"])
class AuthController(@Inject private val registrationService: IRegistrationService) : IAuthController {

    @PostMapping(path = ["/login"])
    override suspend fun login(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        TODO("Not yet implemented")
    }

    @PostMapping(path = ["/register"])
    override suspend fun register(@RequestBody request: RegistrationRequest): ResponseEntity<AuthenticationResponse> {
        registrationService.registerUser(request.user).let { jwtToeken ->
        }
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping(path = ["/login"])
    override suspend fun logout(): String {
        TODO("Not yet implemented")
    }


}