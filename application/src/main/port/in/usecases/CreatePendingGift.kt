package port.`in`.usecases

import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.aggregate.utils.Money

interface CreatePendingGift {
    fun createPendingGift(command: SendGiftCommand): Boolean
    class SendGiftCommand(val amount: Money, val gifterId: GifterId, gifteeId: GifteeId)
}