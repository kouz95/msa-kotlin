package com.microservices.chapter4

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomer = arrayOf(
                Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "MicroService", TelePhone("+44", "12341234"))
        )
    }

    val customers = ConcurrentHashMap(initialCustomer.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun searchCustomers(nameFilter: String) = customers
            .filter { it.value.name.contains(nameFilter, true) }
            .map(Map.Entry<Int, Customer>::value)
            .toFlux()

    override fun createCustomer(customerMono: Mono<Customer>) =
            customerMono.map {
                customers[it.id] = it
                it
            }
}