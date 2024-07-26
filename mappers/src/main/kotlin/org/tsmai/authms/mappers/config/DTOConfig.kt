package org.tsmai.authms.mappers.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.tsmai.authms.mappers.config.DTOConfig.Companion.DTO_MAPPER_IMPLEMENTATION

@AutoConfiguration
@ComponentScan(basePackages = [DTO_MAPPER_IMPLEMENTATION])
class DTOConfig {
    companion object{
        const val DTO_MAPPER_IMPLEMENTATION = "org.tsmai.authms.mappers.dto"
    }
}