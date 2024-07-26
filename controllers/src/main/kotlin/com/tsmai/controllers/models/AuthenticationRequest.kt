package com.tsmai.controllers.models

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor

@Builder
@AllArgsConstructor
@NoArgsConstructor
data class AuthenticationRequest(val username: String, val password: String)