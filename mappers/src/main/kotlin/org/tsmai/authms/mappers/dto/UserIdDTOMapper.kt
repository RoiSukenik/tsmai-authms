package org.tsmai.authms.mappers.dto

import org.mapstruct.Mapper
import org.tsmai.authms.mappers.config.DTOConfig
import org.tsmai.authms.persistence.models.dto.UserIdDTO
import org.tsmai.authms.persistence.models.resource.UserId

@Mapper(implementationPackage = DTOConfig.DTO_MAPPER_IMPLEMENTATION)
interface UserIdDTOMapper {
    fun toUserId(userIdDto: UserIdDTO): UserId
}