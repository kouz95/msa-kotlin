package com.microservices.chapter4

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class CustomerHandler(private val customerService: CustomerService) {
    fun get(serverRequest: ServerRequest) = customerService.getCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap { ok().body(fromValue(it)) }
            .switchIfEmpty(notFound().build())

    fun search(serverRequest: ServerRequest) = ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

    fun create(serverRequest: ServerRequest) = customerService.createCustomer(serverRequest.bodyToMono())
            .flatMap { created(URI.create("/functional/customer/${it.id}")).build() }
}
