package org.tsmai.tests.mappers.resource

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.tsmai.authms.mappers.resource.UserIdMapperImpl
import org.tsmai.authms.mappers.resources.UserIdMapper
import org.tsmai.tests.setup.MappersTestMocks.Companion.user
import org.tsmai.tests.setup.MappersTestMocks.Companion.userDto
import org.tsmai.tests.setup.TestSetup

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserIdMapperTests : TestSetup() {

    private lateinit var userIdMapper: UserIdMapper

    @BeforeAll
    fun setup() {
        userIdMapper = UserIdMapperImpl()
    }

    @Test
    fun testUserIdMapper() {
        val userIdDTOToTest = userIdMapper.toUserIdDTO(user.id)

        assertNotNull(userIdDTOToTest)
        assertEquals(userDto.id.value, userIdDTOToTest.value)
    }
}