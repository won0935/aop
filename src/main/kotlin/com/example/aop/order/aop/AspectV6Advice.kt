package com.example.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before


@Aspect
class AspectV6Advice {

    private val log = KotlinLogging.logger {}

    //메소드 시작 전
    @Before("com.example.aop.order.aop.Pointcuts.orderAndServicePointcut()")
    fun before(joinPoint: JoinPoint) {
        log.info { "[before] ${joinPoint.signature}" }
    }

    //메소드 실행 후
    @AfterReturning("com.example.aop.order.aop.Pointcuts.orderAndServicePointcut()", returning = "result")
    fun afterReturning(joinPoint: JoinPoint, result: Any?) {
        log.info { "[afterReturning] ${joinPoint.signature} , $result" }
    }

    //메소드 실행 중 예외 발생
    @AfterThrowing("com.example.aop.order.aop.Pointcuts.orderAndServicePointcut()", throwing = "ex")
    fun afterThrowing(joinPoint: JoinPoint, ex: Throwable) {
        log.info { "[afterThrowing] ${joinPoint.signature} , $ex" }
    }

    //finally
    @After("com.example.aop.order.aop.Pointcuts.orderAndServicePointcut()")
    fun after(joinPoint: JoinPoint) {
        log.info { "[after] ${joinPoint.signature}" }
    }

}
