package com.tsmai.controllers.models

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.tsmai.authms.persistence.models.resource.User

@Builder
@AllArgsConstructor
@NoArgsConstructor
data class RegistrationRequest(
    val user: User
)