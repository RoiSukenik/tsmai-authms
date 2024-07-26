package org.tsmai.authms.services.exceptions

class UserExistsError(val customMessage: String = "The user already exists.") : RuntimeException() {

}