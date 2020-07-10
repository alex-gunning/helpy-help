package org.helpy.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.helpy.application", "org.helpy.domain", "org.helpy.infrastructure", "org.helpy.web"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
