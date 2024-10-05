package org.tsmai.authms.services.login

fun interface ILoginService {
    fun login(username:String, password:String)
}