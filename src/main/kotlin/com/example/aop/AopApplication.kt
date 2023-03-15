package com.example.aop

import com.example.aop.order.aop.AspectV5Order
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(AspectV5Order.LogAspect::class, AspectV5Order.TransactionAspect::class)
class AopApplication

fun main(args: Array<String>) {
    runApplication<AopApplication>(*args)
}
