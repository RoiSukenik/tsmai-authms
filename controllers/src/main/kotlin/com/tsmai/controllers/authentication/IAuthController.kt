package com.tsmai.controllers.authentication

import com.tsmai.controllers.IApiController
import com.tsmai.controllers.models.AuthenticationRequest
import com.tsmai.controllers.models.AuthenticationResponse
import com.tsmai.controllers.models.LogoutResponse
import com.tsmai.controllers.models.RegistrationRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.tsmai.authms.persistence.models.resource.User
import reactor.core.publisher.Mono


interface IAuthController : IApiController {

    suspend fun login(@RequestBody request: AuthenticationRequest): Mono<ResponseEntity<AuthenticationResponse>>
    suspend fun register(@RequestBody request: RegistrationRequest): Mono<ResponseEntity<AuthenticationResponse>>
    suspend fun logout(): Mono<ResponseEntity<LogoutResponse>>
}