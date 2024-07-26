package org.tsmai.authms.persistence.models.dto

import lombok.NonNull


data class UserIdDTO(
    @NonNull
    val value: String,
) {
    override fun toString(): String {
        return "UserDTO::$value"
    }
}
