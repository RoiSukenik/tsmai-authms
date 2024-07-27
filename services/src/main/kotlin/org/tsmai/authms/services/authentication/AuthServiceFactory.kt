//package org.tsmai.authms.services.authentication
//
//import jakarta.annotation.PostConstruct
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//import org.tsmai.authms.services.authentication.interfaces.IAuthService
//
//@Service
//class AuthServiceFactory(
//    @Autowired private val services: List<IAuthService>
//) {
//    companion object {
//        @JvmStatic
//        private val authServiceCache = HashMap<String, IAuthService>()
//
//        private val log = LoggerFactory.getLogger(AuthServiceFactory::class.java)
//    }
//
//    @PostConstruct
//    fun initServiceCache() {
//        authServiceCache.putAll(services.associateBy(IAuthService::getAuthType))
//        log.info("The following auth services are available: ${authServiceCache.values}")
//    }
//
//    fun getAuthService(type: String): IAuthService {
//        val service = authServiceCache[type] ?: throw RuntimeException("Unknown auth service $type")
//        return service
//    }
//
//}