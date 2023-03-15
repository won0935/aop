package com.example.aop.order


import org.springframework.stereotype.Repository

@Repository
class OrderRepository {
    fun save(itemId: String) {
        require(itemId != "ex")
        sleep(1000)
    }

    private fun sleep(mills: Long) {
        Thread.sleep(mills)
    }
}
