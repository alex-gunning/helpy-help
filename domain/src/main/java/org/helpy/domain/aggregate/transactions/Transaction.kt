package org.helpy.domain.aggregate.transactions

import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.domain.aggregate.utils.Money
import java.time.LocalDateTime

class Deposit(amount: Money, timestamp: LocalDateTime, from: BankAccount)
class Withdrawal(amount: Money, timestamp: LocalDateTime, to: BankAccount)


