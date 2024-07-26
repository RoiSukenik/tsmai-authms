package org.tsmai.tests.setup

import org.tsmai.authms.persistence.models.dto.UserDTO
import org.tsmai.authms.persistence.models.dto.UserIdDTO
import java.util.UUID

internal open class CouchbaseTestMocks {
    companion object {
        private val id1 = UUID.randomUUID().toString()
        private val userIdDto1 = UserIdDTO(id1)
        val userDto1 = UserDTO(
            id = userIdDto1,
            firstname = "testUserFirstName1",
            lastname = "testUserLastName1",
            email = "test1@test.com",
            roles = mutableSetOf(UserDTO.Roles.BASIC_USER),
            password = "testPassword1",
            username = "testUser1",
        )

        private val id2 = UUID.randomUUID().toString()
        private val userIdDto2 = UserIdDTO(id2)
        val userDto2 = UserDTO(
            id = userIdDto2,
            firstname = "testUserFirstName2",
            lastname = "testUserLastName2",
            email = "test2@test.com",
            roles = mutableSetOf(UserDTO.Roles.BASIC_USER),
            password = "testPassword2",
            username = "testUser2"
        )
    }

}