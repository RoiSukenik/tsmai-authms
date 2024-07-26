package org.tsmai.tests.setup

import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.models.dto.UserIdDTO
import org.tsmai.authms.persistence.models.resource.User
import org.tsmai.authms.persistence.models.resource.UserId
import java.util.UUID

internal open class MappersTestMocks {
    companion object {
        private val id = UUID.randomUUID().toString()
        protected val userId = UserId(id)
        val user = User(
            id = userId,
            username = "testUserName",
            firstname = "testUserFirstName",
            lastname = "testUserLastName",
            email = "test@test.com",
            password = "testPassword",
        )

        protected val userIdDto = UserIdDTO(id)
        val userDto = UserDTO(
            id = userIdDto,
            username = "testUserName",
            firstname = "testUserFirstName",
            lastname = "testUserLastName",
            email = "test@test.com",
            password = "testPassword",
        )
    }

}