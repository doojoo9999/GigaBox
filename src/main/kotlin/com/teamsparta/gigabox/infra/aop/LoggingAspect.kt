package com.teamsparta.gigabox.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class LoggingAspect {
    private val logger = LoggerFactory.getLogger("Execution Time Logger")

    @Pointcut("execution(* com.teamsparta.gigabox.domain.*.controller.*.*(..))")
    private fun pointCut(){}

    @Around("pointCut()")
    fun run(joinPoint: ProceedingJoinPoint): Any{
        val stopWatch = StopWatch()

        stopWatch.start()
        val logic = joinPoint.proceed()
        stopWatch.stop()

        val timeElapsedMs = stopWatch.totalTimeMillis

        logger.info("Execution Time: ${timeElapsedMs}ms")

        return logic
    }
}