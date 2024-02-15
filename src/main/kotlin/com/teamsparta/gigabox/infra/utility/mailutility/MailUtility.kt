package com.teamsparta.gigabox.infra.utility.mailutility

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.util.*

@Component
class MailUtility (
    val javaMailSender: JavaMailSender
){

    fun getRandomString() : String {
        val length = 16
        val randomId = UUID.randomUUID().toString().substring(0 until length)

        return randomId
    }

    fun sendMail(email: String) : String {
        val randomString = getRandomString()

        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        helper.setTo(email)
        helper.setSubject("GigaBox 이메일 인증")
        helper.setText("<h1>[이메일 인증]</h1><br>" +
                "a href = http://localhost:8080/api/v1/member/emailAuth?authCode=$randomString")
        helper.setFrom("doojoo0536@gmail.com")
        javaMailSender.send(message)


        return randomString
    }

}