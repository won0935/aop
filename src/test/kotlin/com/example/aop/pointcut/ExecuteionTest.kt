package com.example.aop.pointcut

import com.example.aop.member.MemberServiceImpl
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method

class ExecuteionTest {

    private val log = KotlinLogging.logger {}

    val pointcut = AspectJExpressionPointcut()
    lateinit var helloMethod : Method


    @BeforeEach
    fun init () {
        //public java.lang.String com.example.aop.member.MemberServiceImpl.hello(java.lang.String)
        helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
    }


    @Test
    fun printMethod(){
        log.info { helloMethod }
    }

    @Test
    fun exactMatch(){
        //접근제어자 : public
        //리턴타입 : String
        //선언타입 : com.example.aop.member.MemberServiceImpl
        //메소드명 : hello
        //파라미터 : String
        pointcut.expression = "execution(public String com.example.aop.member.MemberServiceImpl.hello(String))"

        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun allMatch(){
        //접근제어자 : 생략
        //리턴타입 : *
        //선언타입 : 생략
        //메소드명 : *
        //파라미터 : (..)
        pointcut.expression = "execution(* *(..))"

        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchStar1(){
        pointcut.expression =  "execution(* hel*(..))"

        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchStar2(){
        pointcut.expression =  "execution(* *el*(..))"

        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchFalse(){
        pointcut.expression =  "execution(* nono(..))"

        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    }
}
