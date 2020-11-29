package com.microservices.chapter4

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class CustomerController(private val customerService: CustomerService) {
    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) =
            ResponseEntity.ok(customerService.getCustomer(id) as Mono<Customer>)

    @GetMapping("/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            ResponseEntity.ok(customerService.searchCustomers(nameFilter))

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customerMono: Mono<Customer>) =
            ResponseEntity(customerService.createCustomer(customerMono), HttpStatus.CREATED)
}