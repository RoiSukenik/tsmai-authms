//package org.tsmai.tests.factories
//
//import kotlinx.coroutines.runBlocking
//import org.junit.jupiter.api.Disabled
//import org.junit.jupiter.api.Assertions.assertInstanceOf
//import org.junit.jupiter.api.Assertions.assertNotNull
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.Nested
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestInstance
//import org.mockito.Mockito
//import org.tsmai.tests.setup.SpringTestSetup
//import org.tsmai.authms.services.authentication.AuthServiceFactory
//import org.tsmai.authms.services.authentication.auth0.Auth0AuthService
//import org.tsmai.authms.services.authentication.dbauth.DBAuthService
//import org.tsmai.authms.services.authentication.interfaces.AuthServiceType
//import org.tsmai.authms.services.authentication.interfaces.IAuthService
//import org.tsmai.authms.services.authentication.jwt.JwtAuthService
//
//@Disabled
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//internal class AuthFactoryTests : SpringTestSetup() {
//
//    private lateinit var authServiceFactory: AuthServiceFactory
//
//    private lateinit var jwtAuthService: IAuthService
//    private lateinit var auth0Service: IAuthService
//    private lateinit var dbService: IAuthService
//
//    @BeforeAll
//    fun beforeAll() {
//        jwtAuthService = Mockito.spy(JwtAuthService::class.java)
//        dbService = Mockito.spy(DBAuthService::class.java)
//        auth0Service = Mockito.spy(Auth0AuthService::class.java)
//
//        authServiceFactory = AuthServiceFactory(listOf(jwtAuthService, auth0Service, dbService))
//    }
//
//    @Disabled
//    @Test
//    fun assertAuthServiceFactoryLoaded(){
//        assertNotNull(authServiceFactory)
//    }
//
//    @Nested
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    internal inner class JWTAuthServiceTests {
//
//        private lateinit var authService: IAuthService
//
//        @BeforeAll
//        fun beforeAll() = runBlocking {
//            authService = authServiceFactory.getAuthService(AuthServiceType.JWT.name)
//        }
//
//        @Disabled
//        @Test
//        fun testServiceLoadedCorrectly() {
//            assertNotNull(authService)
//            assertInstanceOf(JwtAuthService::class.java, authService)
//        }
//    }
//
//    @Nested
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    internal inner class Auth0AuthServiceTests {
//
//        @Disabled
//        @Test
//        fun testServiceLoadedCorrectly() {
//            val authService = authServiceFactory.getAuthService(AuthServiceType.AUTH0.name)
//            assertNotNull(authService)
//            assertInstanceOf(Auth0AuthService::class.java, authService)
//        }
//    }
//
//    @Nested
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    internal inner class DBAuthServiceTests {
//
//        @Disabled
//        @Test
//        fun testServiceLoadedCorrectly() {
//            val authService = authServiceFactory.getAuthService(AuthServiceType.DB.name)
//            assertNotNull(authService)
//            assertInstanceOf(DBAuthService::class.java, authServiceFactory.getAuthService(AuthServiceType.DB.name))
//        }
//    }
//}