package org.helpy.domain.services

import org.helpy.domain.aggregate.accounts.BankAccount

interface MoveMoney {
   fun moveMoney(ourAccount: BankAccount, theirAccount: BankAccount): Boolean
}

