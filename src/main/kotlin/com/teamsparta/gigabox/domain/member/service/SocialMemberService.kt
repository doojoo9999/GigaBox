package com.teamsparta.gigabox.domain.member.service

import com.example.oauth2loginsample.client.oauth2.kakao.dto.KakaoUserInfoResponse
import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.domain.member.model.OAuth2Provider
import com.teamsparta.gigabox.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class SocialMemberService(
    private val memberRepository: MemberRepository
) {

    fun registerIfAbsent(userInfo: KakaoUserInfoResponse): MemberEntity {
        return if (!memberRepository.existsByProviderAndProviderId(OAuth2Provider.KAKAO, userInfo.id.toString())) {
            val socialMember = MemberEntity.ofKakao(userInfo.id,userInfo.nickname)
            memberRepository.save(socialMember)
        } else {
            memberRepository.findByProviderAndProviderId(OAuth2Provider.KAKAO, userInfo.id.toString())
        }
    }
}