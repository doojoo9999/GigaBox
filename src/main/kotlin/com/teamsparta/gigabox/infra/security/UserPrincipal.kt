package com.teamsparta.gigabox.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val account: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long, account: String, roles: Set<String>): this(
        id,
        account,
        roles.map { SimpleGrantedAuthority("ROLE_$it") }
    )
}
