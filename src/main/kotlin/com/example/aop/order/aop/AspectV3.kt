package com.example.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut


@Aspect
class AspectV3 {

    private val log = KotlinLogging.logger {}

    //order 하위 패키지의 모든 메소드
    @Pointcut("execution(* com.example.aop.order..*(..))")
    private fun orderPointcut() {
    }

    //클래스 이름 패턴이 *Service 인 경우
    @Pointcut("execution(* *..*Service.*(..))")
    private fun servicePointcut() {
    }


    @Around("orderPointcut()") //pointcut 이름
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info { "log $joinPoint" }
        return joinPoint.proceed()
    }

    //order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service 인 경우
    @Around("orderPointcut() && servicePointcut()")
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
