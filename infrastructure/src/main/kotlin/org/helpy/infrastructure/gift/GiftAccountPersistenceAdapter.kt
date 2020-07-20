package org.helpy.infrastructure.gift

import org.helpy.domain.aggregate.accounts.GiftAccount
import org.helpy.domain.aggregate.accounts.GiftAccountId
import org.helpy.domain.ports.out.PendingGiftAccountPort
import org.springframework.stereotype.Repository

@Repository
class GiftAccountPersistenceAdapter(
//        val giftAccountRepository: GiftAccountRepository,
//        val giftAccountMapper: GiftAccountMapper
) : PendingGiftAccountPort {
    override fun savePendingGiftAccount(giftAccount: GiftAccount) {
//        giftAccountRepository.save(giftAccountMapper.toPersistenceModel(giftAccount))
        TODO("Not yet implemented")
    }

    override fun loadPendingGiftAccount(accountId: GiftAccountId): GiftAccount {
        TODO("Not yet implemented")
    }
}