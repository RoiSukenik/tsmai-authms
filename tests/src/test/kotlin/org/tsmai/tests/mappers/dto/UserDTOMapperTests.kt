package org.tsmai.tests.mappers.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.tsmai.authms.mappers.dto.UserDTOMapper
import org.tsmai.authms.mappers.dto.UserDTOMapperImpl
import org.tsmai.authms.mappers.dto.UserIdDTOMapper
import org.tsmai.authms.mappers.dto.UserIdDTOMapperImpl
import org.tsmai.tests.setup.MappersTestMocks.Companion.userDto
import org.tsmai.tests.setup.TestSetup

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserDTOMapperTests : TestSetup() {

    @Spy
    private lateinit var userIdDTOMapper: UserIdDTOMapper

    @InjectMocks
    private lateinit var userDTOMapper: UserDTOMapper

    @BeforeAll
    fun setup() {
        userIdDTOMapper = UserIdDTOMapperImpl()
        userDTOMapper = UserDTOMapperImpl()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testUserDTOMapper() {
        val userToTest = userDTOMapper.toUser(userDto)

        assertNotNull(userToTest)
        assertEquals(userDto.id.value, userToTest.id.value)
        assertEquals(userDto.firstname, userToTest.firstname)
        assertEquals(userDto.email, userToTest.email)
        assertEquals(userDto.password, userToTest.password)

    }


}