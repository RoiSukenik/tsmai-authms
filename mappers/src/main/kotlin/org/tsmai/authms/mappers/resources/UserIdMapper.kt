package org.tsmai.authms.mappers.resources

import org.mapstruct.Mapper
import org.tsmai.authms.mappers.config.ResourceConfig
import org.tsmai.authms.persistence.models.dto.UserIdDTO
import org.tsmai.authms.persistence.models.resource.UserId

@Mapper(implementationPackage = ResourceConfig.RESOURCE_MAPPER_IMPLEMENTATION)
fun interface UserIdMapper {
    fun toUserIdDTO(userId: UserId): UserIdDTO
}