package org.helpy.domain.ports.out

import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId

interface LoadUserAccountPort {
    fun loadUserAccount(gifterId: GifterId): Gifter
    fun loadUserAccount(gifteeId: GifteeId): Giftee
}