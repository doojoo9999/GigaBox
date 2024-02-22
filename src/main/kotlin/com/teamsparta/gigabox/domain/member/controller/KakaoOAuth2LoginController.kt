package com.teamsparta.gigabox.domain.member.controller

import com.teamsparta.gigabox.client.oauth2.kakao.KakaoOAuth2Client
import com.teamsparta.gigabox.domain.member.service.KakaoOAuth2LoginService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class KakaoOAuth2LoginController(
    private val kakaoOAuth2LoginService: KakaoOAuth2LoginService,
    private val kakaoOAuth2Client: KakaoOAuth2Client
) {

    //1. 로그인페이지로 redirect 해주는 api
    @GetMapping("/oauth2/login/kakao")
    fun redirectLoginPage(response: HttpServletResponse) {
        val loginPageUrl = kakaoOAuth2Client.generateLoginPageUrl()
        response.sendRedirect(loginPageUrl)
    }
    //2. AuthorizationCode 를 이용해 사용자 인증을 처리해주는 api
    @GetMapping("/oauth2/callback/kakao")
    fun callback(@RequestParam(name = "code") authorizationCode: String): String {
        return kakaoOAuth2LoginService.login(authorizationCode)

    }
}