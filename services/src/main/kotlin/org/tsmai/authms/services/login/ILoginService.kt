package org.tsmai.authms.services.login

interface ILoginService {
    fun login(username:String, password:String) : String?
}