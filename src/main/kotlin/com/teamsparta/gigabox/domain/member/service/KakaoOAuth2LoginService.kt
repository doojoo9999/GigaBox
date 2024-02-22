package com.teamsparta.gigabox.domain.member.service

import com.teamsparta.gigabox.client.oauth2.kakao.KakaoOAuth2Client
import com.teamsparta.gigabox.domain.member.model.UserRole
import com.teamsparta.gigabox.infra.security.jwt.JwtPlugin
import org.springframework.stereotype.Service

@Service
class KakaoOAuth2LoginService(
    private val kakaoOAuth2Client: KakaoOAuth2Client,
    private val socialMemberService: SocialMemberService,
    private val jwtplugin: JwtPlugin
) {

    fun login(authorizationCode: String): String {
        return kakaoOAuth2Client.getAccessToken(authorizationCode)
            .let { kakaoOAuth2Client.retrieveUserInfo(it) }
            .let { socialMemberService.registerIfAbsent(it) }
            .let { jwtplugin.generatedAccessToken(it.id.toString(),null,UserRole.MEMBER.toString()) }



//        val accessToken = kakaoOAuth2Client.getAccessToken(authorizationCode)
//        val userInfo = kakaoOAuth2Client.retrieveUserInfo(accessToken)
//        val socialMember = socialMemberService.registerIfAbsent(userInfo)
//        val role = UserRole.MEMBER
//
//        return jwtplugin.generatedAccessToken(socialMember.id.toString(),null, role.toString())
    }
}