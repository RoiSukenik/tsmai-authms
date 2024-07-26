package org.tsmai.authms.services.registration

import org.tsmai.authms.persistence.models.resource.User

interface IRegistrationService {

    fun registerUser(user: User):String
}