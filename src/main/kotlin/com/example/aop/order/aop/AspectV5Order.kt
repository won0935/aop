package com.example.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.core.annotation.Order


class AspectV5Order {

    //순서를 정하려면 반드시 클래스로 분리해야함
    @Order(2) //order를 지정해주면 순서대로 실행된다.
    @Aspect
    class LogAspect{
        private val log = KotlinLogging.logger {}

        //다른 파일에서 포인트 컷 가져오기, 경로를 적어주어야 한다.
        @Around("com.example.aop.order.aop.Pointcuts.orderPointcut()")
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {
            log.info { "log $joinPoint" }
            return joinPoint.proceed()
        }
    }

    @Order(1)
    @Aspect
    class TransactionAspect{
        private val log = KotlinLogging.logger {}

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
}
