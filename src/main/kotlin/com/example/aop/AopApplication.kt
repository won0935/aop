package com.example.aop

import com.example.aop.order.aop.AspectV6Advice
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(AspectV6Advice::class)
class AopApplication

fun main(args: Array<String>) {
    runApplication<AopApplication>(*args)
}
