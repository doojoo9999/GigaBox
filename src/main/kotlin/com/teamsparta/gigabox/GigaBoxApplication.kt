package com.teamsparta.gigabox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAspectJAutoProxy // AOP 적용하기 위해서 사용
@SpringBootApplication
@EnableJpaAuditing
class GigaBoxApplication

fun main(args: Array<String>) {
    runApplication<GigaBoxApplication>(*args)
}
