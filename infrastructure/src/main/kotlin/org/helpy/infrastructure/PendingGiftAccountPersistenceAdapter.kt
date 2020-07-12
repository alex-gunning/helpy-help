package org.helpy.infrastructure

import org.helpy.domain.aggregate.accounts.GiftAccount
import org.helpy.domain.aggregate.accounts.GiftAccountId
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.aggregate.utils.Money
import org.helpy.domain.ports.out.PendingGiftAccountPort
import org.springframework.stereotype.Repository

@Repository
class PendingGiftAccountPersistenceAdapter: PendingGiftAccountPort {
    override fun savePendingGiftAccount(giftAccount: GiftAccount) {
        TODO("Not yet implemented")
    }

    override fun loadPendingGiftAccount(accountId: GiftAccountId): GiftAccount {
        TODO("Not yet implemented")
    }
}