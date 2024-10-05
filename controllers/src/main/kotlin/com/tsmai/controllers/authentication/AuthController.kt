package com.tsmai.controllers.authentication

import com.tsmai.controllers.models.AuthenticationRequest
import com.tsmai.controllers.models.AuthenticationResponse
import com.tsmai.controllers.models.LogoutResponse
import com.tsmai.controllers.models.RegistrationRequest
import jakarta.inject.Inject
import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.tsmai.authms.services.login.ILoginService
import org.tsmai.authms.services.registration.IRegistrationService
import reactor.core.publisher.Mono

@RequestMapping(path = ["/authentication"])
class AuthController(
    @Inject private val registrationService: IRegistrationService,
    @Inject private val loginService: ILoginService
) : IAuthController {

    @PostMapping(path = ["/login"])
    override suspend fun login(@RequestBody request: AuthenticationRequest): Mono<ResponseEntity<AuthenticationResponse>> {
        return loginService.login(request.username, request.password).let { jwtToken ->
            return@let mono { ResponseEntity(AuthenticationResponse(jwtToken.toString()), HttpStatus.OK) }
        }
    }

    @PostMapping(path = ["/register"])
    override suspend fun register(@RequestBody request: RegistrationRequest): Mono<ResponseEntity<AuthenticationResponse>> {
        return registrationService.registerUser(request.user).let { jwtToken ->
            return@let mono { ResponseEntity(AuthenticationResponse(jwtToken), HttpStatus.CREATED) }
        }
    }

    @GetMapping(path = ["/logout"])
    override suspend fun logout(): Mono<ResponseEntity<LogoutResponse>> {
        TODO("Not yet implemented")
    }


}