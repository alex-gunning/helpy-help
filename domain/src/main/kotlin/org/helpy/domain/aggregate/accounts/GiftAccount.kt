package org.helpy.domain.aggregate.accounts

import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.utils.Money
import java.time.LocalDateTime
import java.util.UUID

inline class GiftAccountId(val id: UUID = UUID.randomUUID())
data class GiftAccount(val accountId: GiftAccountId,
                       val gifter: Gifter,
                       val giftee: Giftee,
                       val credits: Money,
                       val createdAt: LocalDateTime = LocalDateTime.now(),
                       val redeemed: Boolean = false)
