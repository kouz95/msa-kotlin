package com.microservices.chapter3

import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController(private val customers: ConcurrentHashMap<Int, Customer>) {
    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = customers[id]

    @GetMapping("/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customers.filter { it.value.name.contains(nameFilter, true) }
                    .map(Map.Entry<Int, Customer>::value).toList()

    @PostMapping("/customer/{id}")
    fun createCustomer(@RequestBody customer: Customer) {
        customers[customer.id] = customer
    }

    @DeleteMapping("/customer/{id}")
    fun deleteCustomer(@PathVariable id: Int) = customers.remove(id)

    @PutMapping("/customer/{id}")
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) {
        customers.remove(id)
        customers[id] = customer
    }
}
