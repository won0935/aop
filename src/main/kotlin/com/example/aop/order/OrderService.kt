package com.example.aop.order


import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}
