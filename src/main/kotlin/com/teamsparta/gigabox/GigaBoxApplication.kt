package com.teamsparta.gigabox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class GigaBoxApplication

fun main(args: Array<String>) {
    runApplication<GigaBoxApplication>(*args)
}
