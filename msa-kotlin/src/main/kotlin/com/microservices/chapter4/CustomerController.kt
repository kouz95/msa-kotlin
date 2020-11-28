package com.microservices.chapter4

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(private val customerService: CustomerService) {
    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = ResponseEntity.ok(customerService.getCustomer(id) as Customer)

    @GetMapping("/customers")
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.searchCustomers(nameFilter)
}