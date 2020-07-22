package org.helpy.infrastructure.db

import com.mysql.cj.jdbc.MysqlDataSource
import org.helpy.infrastructure.db.exception.ExceptionTranslator
import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.jooq.impl.DefaultExecuteListenerProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@ComponentScan("org.helpy.infrastructure.dao")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
class PersistenceContext(val environment: Environment) {
    @Bean
    open fun dataSource(): DataSource {
        val dataSource = MysqlDataSource()
        dataSource.setUrl(environment.getRequiredProperty("db.url"))
        dataSource.setUser(environment.getRequiredProperty("db.username"))
        dataSource.setPassword(environment.getRequiredProperty("db.password"))
        return dataSource
    }

    @Bean
    fun transactionAwareDataSource(): TransactionAwareDataSourceProxy {
        return TransactionAwareDataSourceProxy(dataSource())
    }

    @Bean
    fun transactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }

    @Bean
    fun connectionProvider(): DataSourceConnectionProvider {
        return DataSourceConnectionProvider(transactionAwareDataSource())
    }

    @Bean
    fun exceptionTransformer(): ExceptionTranslator {
        return ExceptionTranslator()
    }

    @Bean
    fun dsl(): DefaultDSLContext {
        return DefaultDSLContext(configuration())
    }

    @Bean
    fun configuration(): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(connectionProvider())
        jooqConfiguration.set(DefaultExecuteListenerProvider(exceptionTransformer()))
        val sqlDialectName = environment.getRequiredProperty("jooq.sql.dialect")
        val dialect: SQLDialect = SQLDialect.valueOf(sqlDialectName)
        jooqConfiguration.set(dialect)
        return jooqConfiguration
    }
}