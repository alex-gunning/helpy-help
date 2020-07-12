package org.helpy.domain.aggregate.utils

import org.springframework.beans.factory.annotation.Value
import java.math.BigDecimal

data class Money(val amount: BigDecimal,
                 val currencyCode: String = DEFAULT_CURRENCY_CODE,
                 val currencySymbol: String = DEFAULT_CURRENCY_SYMBOL) {
    companion object {
        @Value("\${money.currency.code.default}")
        var DEFAULT_CURRENCY_CODE: String = ""

        @Value("\${money.currency.symbol.default}")
        var DEFAULT_CURRENCY_SYMBOL: String = ""
    }
    operator fun plus(other: Money): Money {
        return this.copy(amount = this.amount.plus(other.amount))
    }
    operator fun minus(other: Money): Money {
        return this.copy(amount = this.amount.minus(other.amount))
    }
    override operator fun equals(other: Any?): Boolean {
        return (other is Money)
                && this.amount == other.amount
                && this.currencyCode == other.currencyCode
                && this.currencySymbol == other.currencySymbol
    }
    override fun toString(): String {
        return "$currencySymbol $amount"
    }
    override fun hashCode(): Int {
        var result = amount.hashCode()
        result = 31 * result + currencyCode.hashCode()
        result = 31 * result + currencySymbol.hashCode()
        return result
    }
}
val Penniless = Money(BigDecimal(0))
