package org.tsmai.tests.mappers.resource

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.tsmai.authms.mappers.resource.UserIdMapperImpl
import org.tsmai.authms.mappers.resource.UserMapperImpl
import org.tsmai.authms.mappers.resources.UserIdMapper
import org.tsmai.authms.mappers.resources.UserMapper
import org.tsmai.tests.setup.MappersTestMocks.Companion.user
import org.tsmai.tests.setup.MappersTestMocks.Companion.userDto
import org.tsmai.tests.setup.TestSetup

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserMapperTests : TestSetup() {

    @Spy
    private lateinit var userIdMapper: UserIdMapper

    @InjectMocks
    private lateinit var userMapper: UserMapper

    @BeforeAll
    fun setup() {
        userIdMapper = UserIdMapperImpl()
        userMapper = UserMapperImpl()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun userMapper() {
        val userDTOToTest = userMapper.toUserDTO(user)

        assertNotNull(userDTOToTest)
        assertEquals(userDto.firstname, userDTOToTest.firstname)
        assertEquals(userDto.password, userDTOToTest.password)
        assertEquals(userDto.email, userDTOToTest.email)
    }
}