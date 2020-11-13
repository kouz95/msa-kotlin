package com.microservices.chapter2.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class FirstController {
    @RequestMapping(value = ["/user"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello() = "hello world"
}
