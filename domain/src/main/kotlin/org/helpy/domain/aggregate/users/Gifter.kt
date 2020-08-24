package org.helpy.domain.aggregate.users

import org.helpy.domain.aggregate.accounts.BankAccount
import java.util.UUID

inline class GifterId(val id: UUID)
data class Gifter(val gifterId: GifterId?,
                  val firstname: String,
                  val surname: String,
                  val bankAccount: BankAccount)
