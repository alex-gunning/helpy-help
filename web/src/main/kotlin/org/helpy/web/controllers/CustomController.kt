package org.helpy.web.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomController {
    @GetMapping("/")
    fun bleh(): String {
        return "blehBleh"
    }
}
