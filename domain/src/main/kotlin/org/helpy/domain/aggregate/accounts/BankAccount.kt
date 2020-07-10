package org.helpy.domain.aggregate.accounts

import java.util.UUID

enum class Bank {
    ABSA,
    CAPITEC,
    STANDARD_BANK,
    FIRST_NATIONAL_BANK
}
data class BankAccount(val accountId: UUID,
                       val provider: Bank,
                       val branch: String,
                       val accountNumber: String)

