package org.tsmai.authms.persistence.models.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import lombok.NonNull
import org.springframework.data.annotation.Id
import org.springframework.data.couchbase.core.mapping.Document
import org.springframework.data.couchbase.core.mapping.Field
import org.springframework.data.couchbase.repository.Collection
import org.springframework.data.couchbase.repository.Scope
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document
@Scope("user")
@Collection("users")
data class UserDTO(
    @NonNull
    @Id
    val id: UserIdDTO,
    @Field
    val firstname: String,
    @Field
    val lastname: String,
    @Field
    val email: String,
    @Field
    var roles: MutableSet<Roles>? = mutableSetOf(),
    @Field
    @get:JvmName("getUserPassword") @set:JvmName("setUserPassword") private var password: String,
    @Field
    @get:JvmName("getUsername") @set:JvmName("setUsername") private var username: String
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles?.map { role -> SimpleGrantedAuthority(role.name) }?.toMutableSet() ?: mutableListOf(SimpleGrantedAuthority(
            Roles.BASIC_USER.toString()
        ))
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    enum class Roles {
        ADMIN, BASIC_USER
    }
}
