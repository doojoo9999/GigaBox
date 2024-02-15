package com.teamsparta.gigabox.domain.member.service

import com.teamsparta.gigabox.domain.member.dto.request.EmailAuthRequest
import com.teamsparta.gigabox.domain.member.dto.request.SendMailRequest
import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest
import com.teamsparta.gigabox.domain.member.model.MailEntity
import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.domain.member.model.UserRole
import com.teamsparta.gigabox.domain.member.repository.MailRepository
import com.teamsparta.gigabox.domain.member.repository.MemberRepository
import com.teamsparta.gigabox.infra.utility.mailutility.MailUtility
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository : MemberRepository,
    private val passwordEncoder : PasswordEncoder,
    private val mailUtility : MailUtility,
    private val mailRepository: MailRepository,
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

    override fun sendMail(request: SendMailRequest) {
        val randomString = mailUtility.sendMail(request.email)

        mailRepository.save(
            MailEntity(
                email = request.email,
                authCode = randomString
            )
        )
    }

    @Transactional
    override fun emailAuth(authCode : String) {
        val authCodeCheck = mailRepository.findByAuthCode(authCode)
        val userCheck = memberRepository.findByEmail(authCodeCheck.email)

        userCheck.role = UserRole.MEMBER

        memberRepository.save(userCheck)
    }
}