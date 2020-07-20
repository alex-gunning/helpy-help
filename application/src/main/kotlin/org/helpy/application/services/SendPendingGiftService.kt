package org.helpy.application.services

import org.helpy.domain.ports.`in`.usecases.CreatePendingDepositGiftUseCase
import org.helpy.domain.ports.`in`.usecases.SendPendingDepositGiftAccountCommand
import org.helpy.domain.aggregate.accounts.GiftAccount
import org.helpy.domain.aggregate.transactions.ExternalTransactionIdentifier
import org.helpy.domain.aggregate.transactions.TemporaryDepositAccount
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.helpy.domain.ports.out.PendingGiftAccountPort
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SendPendingGiftService(
        private val userAccountPort: LoadUserAccountPort,
        private val pendingGiftAccountPort: PendingGiftAccountPort
) : CreatePendingDepositGiftUseCase {
    override fun createPendingMoneyGiftAccount(command: SendPendingDepositGiftAccountCommand): TemporaryDepositAccount? {
        val giftee = userAccountPort.loadUserAccount(command.gifteeId)
        val gifter = userAccountPort.loadUserAccount(command.gifterId)
        val giftAccount = GiftAccount(gifter = gifter, giftee = giftee, credits = command.amount)
        pendingGiftAccountPort.savePendingGiftAccount(giftAccount)

        println(giftAccount)

//        TODO("Need a port for creating an external transaction")
        return TemporaryDepositAccount(ExternalTransactionIdentifier(UUID.randomUUID()))
    }

}