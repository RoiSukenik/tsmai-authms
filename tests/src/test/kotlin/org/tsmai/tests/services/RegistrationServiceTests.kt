//package org.tsmai.tests.services
//
//import org.junit.jupiter.api.AfterAll
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.TestInstance
//import org.mockito.Mockito
//import org.mockito.MockitoAnnotations
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.tsmai.tests.setup.SpringTestSetup
//import org.tsmai.authms.services.authentication.AuthServiceFactory
//import org.tsmai.authms.services.authentication.auth0.Auth0AuthService
//import org.tsmai.authms.services.authentication.dbauth.DBAuthService
//import org.tsmai.authms.services.authentication.jwt.JwtAuthService
//import org.tsmai.authms.services.registration.IRegistrationService
//
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//internal class RegistrationServiceTests(@Autowired private val registrationService: IRegistrationService) :
//    SpringTestSetup() {
//
//    @MockBean
//    private lateinit var authServiceFactory: AuthServiceFactory
//
//    @MockBean
//    private lateinit var jwtAuthService: JwtAuthService
//
//    @MockBean
//    private lateinit var auth0Service: Auth0AuthService
//
//    @MockBean
//    private lateinit var dbService: DBAuthService
//
//
//    companion object {
//        private val log = LoggerFactory.getLogger(RegistrationServiceTests::class.java)
//    }
//
//
//    @BeforeAll
//    fun beforeAll() {
//        MockitoAnnotations.openMocks(this)
//    }
//
//    @AfterAll
//    fun afterAll() {
//        Mockito.reset(authServiceFactory, auth0Service, dbService, jwtAuthService)
//    }
//
//
//
//}