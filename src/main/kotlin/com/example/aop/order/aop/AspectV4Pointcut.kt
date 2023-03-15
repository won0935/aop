package com.example.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut


@Aspect
class AspectV4Pointcut {

    private val log = KotlinLogging.logger {}

    //다른 파일에서 포인트 컷 가져오기, 경로를 적어주어야 한다.
    @Around("com.example.aop.order.aop.Pointcuts.orderPointcut()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info { "log $joinPoint" }
        return joinPoint.proceed()
    }

    @Around("com.example.aop.order.aop.Pointcuts.orderAndServicePointcut()")
    fun doTx(joinPoint: ProceedingJoinPoint): Any? {
        return runCatching {
            log.info { "[트랜잭션 시작] ${joinPoint.signature}" }
            val result = joinPoint.proceed()
            log.info { "[트랜잭션 커밋] ${joinPoint.signature}" }
            result
        }.onFailure {
            log.info { "[트랜잭션 롤백] ${joinPoint.signature}" }
            throw it
        }.getOrNull()
    }
}
