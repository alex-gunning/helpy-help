package org.helpy.domain.aggregate.users

import org.helpy.domain.aggregate.accounts.BankAccount
import java.util.UUID

data class Provider(val providerId: UUID,
                    val name: String,
                    val address: String,
                    val bankAccount: BankAccount)