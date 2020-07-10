package org.helpy.web

import org.helpy.domain.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomController(
        val customerService: CustomerService
) {

    @GetMapping("/")
    fun bleh(): String {
        return "blehBleh"
    }
    @GetMapping("/customer")
    fun customer(): String {
        return customerService.fetch(1).name
    }
}
