package org.tsmai.authms.services.registration

import org.tsmai.authms.persistence.models.resource.User

fun interface IRegistrationService {

    fun registerUser(user: User):String
}