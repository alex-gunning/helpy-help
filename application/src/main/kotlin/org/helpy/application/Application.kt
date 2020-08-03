package org.helpy.application

import arrow.core.Id
import arrow.core.Option
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = [
    "org.helpy.application",
    "org.helpy.domain",
    "org.helpy.infrastructure",
    "org.helpy.web"
])
@EntityScan("org.helpy.infrastructure")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
