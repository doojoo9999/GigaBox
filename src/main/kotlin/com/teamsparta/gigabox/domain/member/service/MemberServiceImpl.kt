package com.teamsparta.gigabox.domain.member.service

import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest
import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.domain.member.model.UserRole
import com.teamsparta.gigabox.domain.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository : MemberRepository,
    private val passwordEncoder : PasswordEncoder,
) : MemberService {
    override fun signUp(request: SignUpRequest) {

        memberRepository.save(MemberEntity(
            account = request.account,
            password = passwordEncoder.encode(request.password),
            birthDate = request.birthDate,
            phoneNumber = request.phoneNumber,
            role = UserRole.PRE_MEMBER
        ))

    }
}