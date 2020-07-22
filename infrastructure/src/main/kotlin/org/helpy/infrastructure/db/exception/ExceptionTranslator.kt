package org.helpy.infrastructure.db.exception

import org.jooq.ExecuteContext
import org.jooq.SQLDialect
import org.jooq.impl.DefaultExecuteListener
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator
import org.springframework.jdbc.support.SQLExceptionTranslator

class ExceptionTranslator: DefaultExecuteListener() {
    override fun exception(context: ExecuteContext): Unit {
        val dialect: SQLDialect = context.configuration().dialect()
        val translator: SQLExceptionTranslator = SQLErrorCodeSQLExceptionTranslator(dialect.name)
        context.exception(translator
                .translate("Access database using Jooq", context.sql(), context.sqlException()))
    }
}