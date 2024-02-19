package com.teamsparta.gigabox.domain.member.repository

import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.domain.member.model.OAuth2Provider
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository <MemberEntity, Long>{

    fun findByEmail (email:String) : MemberEntity

    fun findByAccount(account: String): MemberEntity?
    fun existsByProviderAndProviderId(kakao: OAuth2Provider, toString: String): Boolean
    fun findByProviderAndProviderId(kakao: OAuth2Provider, toString: String): MemberEntity

}