package org.helpy.domain.aggregate.transactions

import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.domain.aggregate.utils.Money
import java.time.LocalDateTime
import java.util.UUID

inline class ExternalTransactionIdentifier(val id: UUID)
inline class TemporaryDepositAccount(val id: ExternalTransactionIdentifier)

data class Deposit(val amount: Money, val timestamp: LocalDateTime, val from: BankAccount)
data class Withdrawal(val amount: Money, val timestamp: LocalDateTime, val to: BankAccount)
