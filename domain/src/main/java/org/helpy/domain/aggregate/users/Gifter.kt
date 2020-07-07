package org.helpy.domain.aggregate.users

import org.helpy.domain.aggregate.accounts.BankAccount
import java.util.UUID

data class Gifter(val gifterId: UUID,
                  val firstname: String,
                  val surname: String,
                  val bankAccount: BankAccount)
