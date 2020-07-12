package org.helpy.domain.ports.out

import org.helpy.domain.aggregate.accounts.GiftAccount
import org.helpy.domain.aggregate.accounts.GiftAccountId
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.aggregate.utils.Money

interface PendingGiftAccountPort {
    fun savePendingGiftAccount(giftAccount: GiftAccount): Unit
    fun loadPendingGiftAccount(accountId: GiftAccountId): GiftAccount
}