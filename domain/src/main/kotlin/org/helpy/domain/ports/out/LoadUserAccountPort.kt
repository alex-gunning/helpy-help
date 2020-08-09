package org.helpy.domain.ports.out

import arrow.core.Either
import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId

interface LoadUserAccountPort {
    fun loadUserAccount(gifterId: GifterId): Either<Throwable, Gifter>
    fun loadUserAccount(gifteeId: GifteeId): Either<Throwable, Giftee>
}