package org.helpy.application.ports.out

import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.aggregate.utils.Money

interface SavePendingGiftAccountPort {
    fun savePendingGiftAccount(money: Money, gifterId: GifterId, gifteeId: GifteeId): Unit
}