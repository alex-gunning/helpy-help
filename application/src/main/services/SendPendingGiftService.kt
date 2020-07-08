package org.helpy.application.services

import org.helpy.domain.aggregate.transactions.TemporaryDepositAccount
import ports.`in`.usecases.CreatePendingDepositGiftUseCase
import ports.`in`.usecases.SendPendingDepositGiftCommand

class SendPendingGiftService: CreatePendingDepositGiftUseCase {
    override fun createPendingMoneyGiftUseCase(command: SendPendingDepositGiftCommand): TemporaryDepositAccount? {
        TODO("Not yet implemented")
    }
}