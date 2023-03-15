package com.example.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect


@Aspect
class AspectV1 {

    private val log = KotlinLogging.logger {}

    @Around("execution(* com.example.aop.order..*(..))") //order 하위 패키지의 모든 메소드
    fun doLog(joinPoint: ProceedingJoinPoint) : Any? {
        log.info { "log $joinPoint" }
        return joinPoint.proceed()
    }

}
