package org.helpy.application.ports.out

import org.helpy.domain.aggregate.accounts.GiftAccount
import org.helpy.domain.aggregate.accounts.GiftAccountId

interface LoadPendingGiftAccountPort {
    fun loadPendingGiftAccount(accountId: GiftAccountId): GiftAccount
}

