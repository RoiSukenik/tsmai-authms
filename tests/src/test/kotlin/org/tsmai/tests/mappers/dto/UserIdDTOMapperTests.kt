package org.tsmai.tests.mappers.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.tsmai.authms.mappers.dto.UserIdDTOMapper
import org.tsmai.authms.mappers.dto.UserIdDTOMapperImpl
import org.tsmai.tests.setup.MappersTestMocks.Companion.user
import org.tsmai.tests.setup.MappersTestMocks.Companion.userDto
import org.tsmai.tests.setup.TestSetup

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserIdDTOMapperTests : TestSetup() {

    private lateinit var userIdDTOMapper: UserIdDTOMapper

    @BeforeAll
    fun setup() {
        userIdDTOMapper = UserIdDTOMapperImpl()
    }

    @Test
    fun testUserIdDTOMapper() {
        val userIdToTest = userIdDTOMapper.toUserId(userDto.id)

        assertNotNull(userIdToTest)
        assertEquals(user.id.value, userIdToTest.value)
    }

}