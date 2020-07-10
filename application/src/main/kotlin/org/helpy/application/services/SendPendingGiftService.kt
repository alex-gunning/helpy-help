package org.helpy.application.services

import org.helpy.application.ports.`in`.usecases.CreatePendingDepositGiftUseCase
import org.helpy.application.ports.`in`.usecases.SendPendingDepositGiftCommand
import org.helpy.domain.aggregate.transactions.TemporaryDepositAccount

class SendPendingGiftService: CreatePendingDepositGiftUseCase {
    override fun createPendingMoneyGift(command: SendPendingDepositGiftCommand): TemporaryDepositAccount? {
       TODO("Need a port for persisting this transaction")
    }
}