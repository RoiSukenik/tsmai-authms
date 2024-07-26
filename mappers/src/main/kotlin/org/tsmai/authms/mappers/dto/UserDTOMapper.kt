package org.tsmai.authms.mappers.dto

import org.mapstruct.Mapper
import org.tsmai.authms.mappers.config.DTOConfig
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.models.resource.User


@Mapper(implementationPackage = DTOConfig.DTO_MAPPER_IMPLEMENTATION, uses = [UserIdDTOMapper::class])
interface UserDTOMapper {
    fun toUser(user: UserDTO): User
}