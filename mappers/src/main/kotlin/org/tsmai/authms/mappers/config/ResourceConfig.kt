package org.tsmai.authms.mappers.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.tsmai.authms.mappers.config.ResourceConfig.Companion.RESOURCE_MAPPER_IMPLEMENTATION

@AutoConfiguration
@ComponentScan(basePackages = [RESOURCE_MAPPER_IMPLEMENTATION])
class ResourceConfig {
    companion object{
        const val RESOURCE_MAPPER_IMPLEMENTATION = "org.tsmai.authms.mappers.resource"
    }
}