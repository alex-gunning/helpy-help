package org.helpy.domain.ports.out

import arrow.core.Either
import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.errors.LoadUserAccountError

interface LoadUserAccountPort {
    fun loadUserAccount(gifterId: GifterId): Either<LoadUserAccountError, Gifter>
    fun loadUserAccount(gifteeId: GifteeId): Either<LoadUserAccountError, Giftee>
}