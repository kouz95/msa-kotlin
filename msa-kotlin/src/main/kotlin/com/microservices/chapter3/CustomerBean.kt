package com.microservices.chapter3

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ConcurrentHashMap

@Configuration
class CustomerBean {
    @Bean
    fun customers() = ConcurrentHashMap(
            arrayOf(
                    Customer(1, "Kotlin"),
                    Customer(2, "Spring"),
                    Customer(3, "Microservice")
            ).associateBy(Customer::id)
    )
}
