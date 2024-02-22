package com.teamsparta.gigabox.domain.member.service

//import org.springframework.security.crypto.password.PasswordEncoder
import com.teamsparta.gigabox.domain.exception.InvalidCredentialException
import com.teamsparta.gigabox.domain.exception.ModelNotFoundException
import com.teamsparta.gigabox.domain.member.dto.request.LoginRequest
import com.teamsparta.gigabox.domain.member.dto.request.SendMailRequest
import com.teamsparta.gigabox.domain.member.dto.request.SignUpRequest
import com.teamsparta.gigabox.domain.member.dto.response.LoginResponse
import com.teamsparta.gigabox.domain.member.model.MailEntity
import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.domain.member.model.UserRole
import com.teamsparta.gigabox.domain.member.repository.MailRepository
import com.teamsparta.gigabox.domain.member.repository.MemberRepository
import com.teamsparta.gigabox.infra.security.jwt.JwtPlugin
import com.teamsparta.gigabox.infra.utility.mailutility.MailUtility
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val mailUtility: MailUtility,
    private val mailRepository: MailRepository,
    private val jwtPlugin: JwtPlugin
) : MemberService {
    override fun login(request: LoginRequest): LoginResponse {
        val member = memberRepository.findByAccount(request.account)
            ?: throw ModelNotFoundException("member", null)

        if (member.role.name != request.role || !passwordEncoder.matches(request.password, member.password)) {
            throw InvalidCredentialException()
        }

        return LoginResponse(
            accessToken = jwtPlugin.generatedAccessToken(
                subject = member.id.toString(),
                account = member.account,
                role = member.role.name
            )
        )
    }


    override fun signUp(request: SignUpRequest) {

        memberRepository.save(
            MemberEntity(
                account = request.account,
                password = passwordEncoder.encode(request.password),
                email = request.email,
                birthDate = request.birthDate,
                phoneNumber = request.phoneNumber,
                providerId = null,
                provider = null,
                nickname = null,
                role = UserRole.PRE_MEMBER
            )
        )
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
    override fun emailAuth(authCode: String) {
        val authCodeCheck = mailRepository.findByAuthCode(authCode)
        val userCheck = memberRepository.findByEmail(authCodeCheck.email)

        userCheck.role = UserRole.MEMBER

        memberRepository.save(userCheck)
    }
}