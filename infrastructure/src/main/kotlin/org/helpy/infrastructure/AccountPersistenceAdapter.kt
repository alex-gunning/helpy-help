package org.helpy.infrastructure

import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.springframework.stereotype.Repository

@Repository
class AccountPersistenceAdapter: LoadUserAccountPort {
    override fun loadGifterAccount(gifterId: GifterId): Gifter {
        TODO("Load Gifter account via JPA")
    }

    override fun loadGifteeAccount(gifteeId: GifteeId): Giftee {
        TODO("Load Giftee account via JPS")
    }

}