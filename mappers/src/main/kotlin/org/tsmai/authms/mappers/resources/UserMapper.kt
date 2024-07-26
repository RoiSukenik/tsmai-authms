package org.tsmai.authms.mappers.resources

import org.mapstruct.Mapper
import org.tsmai.authms.mappers.config.ResourceConfig
import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.models.resource.User

@Mapper(
    implementationPackage = ResourceConfig.RESOURCE_MAPPER_IMPLEMENTATION,
    uses = [UserIdMapper::class]
)
interface UserMapper {
    fun toUserDTO(user: User): UserDTO

}
