package org.helpy.application

import org.flywaydb.core.Flyway
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Configuration
@ConfigurationProperties(prefix = "db")
data class DBConfig(var url: String = "", var port: String = "", var username: String = "", var password: String = "")

@Component
class FlywayMigrations(val dbconfig: DBConfig) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(p0: ContextRefreshedEvent) {
        println("Running Migrations")
        Flyway
                .configure()
                .createSchemas(true)
                .schemas("customers")
                .dataSource("jdbc:mysql://${dbconfig.url}:${dbconfig.port}?useSSL=false", dbconfig.username, dbconfig.password)
                .load()
                .migrate()
//        TODO("useSSL must be true when running in production")
    }
}
