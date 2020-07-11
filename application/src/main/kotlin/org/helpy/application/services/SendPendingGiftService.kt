package org.helpy.application.services

import org.helpy.domain.CreatePendingDepositGiftUseCase
import org.helpy.domain.SendPendingDepositGiftAccountCommand
import org.helpy.domain.aggregate.accounts.GiftAccount
import org.helpy.domain.aggregate.transactions.ExternalTransactionIdentifier
import org.helpy.domain.aggregate.transactions.TemporaryDepositAccount
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SendPendingGiftService(private val userAccountPort: LoadUserAccountPort): CreatePendingDepositGiftUseCase {
    override fun createPendingMoneyGiftAccount(command: SendPendingDepositGiftAccountCommand): TemporaryDepositAccount? {
        val giftee = userAccountPort.loadGifteeAccount(command.gifteeId)
        val gifter = userAccountPort.loadGifterAccount(command.gifterId)
        val giftAccount = GiftAccount(gifter = gifter, giftee = giftee, credits = command.amount)

//        TODO("Need a port for creating an external transaction")
        return TemporaryDepositAccount(ExternalTransactionIdentifier(UUID.randomUUID()))
    }

}