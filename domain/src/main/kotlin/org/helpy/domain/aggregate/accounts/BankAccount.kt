package org.helpy.domain.aggregate.accounts

import java.util.UUID

data class BankAccount(val accountId: UUID,
                       val provider: String,
                       val branchCode: String,
                       val accountNumber: String)

