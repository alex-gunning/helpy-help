package org.helpy.domain.ports.out

import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId

interface LoadUserAccountPort {
    fun loadGifterAccount(gifterId: GifterId): Gifter
    fun loadGifteeAccount(gifteeId: GifteeId): Giftee
}