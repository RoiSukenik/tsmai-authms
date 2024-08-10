package com.tsmai.controllers.models

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor

@Builder
@AllArgsConstructor
@NoArgsConstructor
class LogoutResponse(private val loggedOut: Boolean) {
}