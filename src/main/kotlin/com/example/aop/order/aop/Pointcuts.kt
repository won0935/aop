package com.example.aop.order.aop

import org.aspectj.lang.annotation.Pointcut


//포인트컷을 다른 파일로 세팅
class Pointcuts {

    //order 하위 패키지의 모든 메소드
    @Pointcut("execution(* com.example.aop.order..*(..))")
    fun orderPointcut() {
    }

    //클래스 이름 패턴이 *Service 인 경우
    @Pointcut("execution(* *..*Service.*(..))")
    fun servicePointcut() {
    }

    //order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service 인 경우
    @Pointcut("orderPointcut() && servicePointcut()")
    fun orderAndServicePointcut() {
    }

}
