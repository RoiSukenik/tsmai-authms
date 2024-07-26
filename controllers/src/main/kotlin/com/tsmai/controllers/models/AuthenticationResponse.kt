package com.tsmai.controllers.models

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor

@Builder
@AllArgsConstructor
@NoArgsConstructor
data class AuthenticationResponse(private val token: String?)