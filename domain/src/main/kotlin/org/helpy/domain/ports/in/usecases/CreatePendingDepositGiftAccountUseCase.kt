package org.helpy.domain.ports.`in`.usecases

import org.helpy.domain.aggregate.transactions.TemporaryDepositAccount
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.aggregate.utils.Money

data class SendPendingDepositGiftAccountCommand(val amount: Money, val gifterId: GifterId, val gifteeId: GifteeId)
interface CreatePendingDepositGiftUseCase {
    // Should create a temporary identifier to which to pay the money
    fun createPendingMoneyGiftAccount(command: SendPendingDepositGiftAccountCommand): TemporaryDepositAccount?
}