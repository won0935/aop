package com.example.aop

import com.example.aop.order.OrderRepository
import com.example.aop.order.OrderService
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AopTest {

    private val log = KotlinLogging.logger {}

    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var orderRepository: OrderRepository


    @Test
    fun aopInfo() {
        log.info { "isAopProxy, orderService = ${AopUtils.isAopProxy(orderService)}" }
        log.info { "isAopProxy, orderRepository = ${AopUtils.isAopProxy(orderRepository)}" }
    }

    @Test
    fun success(){
        orderService.orderItem("item")
    }

    @Test
    fun exception(){
        assertThrows<IllegalArgumentException> {
            orderService.orderItem("ex")
        }
    }



}
