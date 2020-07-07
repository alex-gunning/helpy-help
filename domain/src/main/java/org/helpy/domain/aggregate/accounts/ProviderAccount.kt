package org.helpy.domain.aggregate.accounts

import org.helpy.domain.aggregate.users.Provider
import java.time.LocalDateTime
import java.util.UUID

data class ProviderAccount(val provider: Provider,
                           val accountId: UUID = UUID.randomUUID(),
                           val redeemedAgainst: GiftAccount?,
                           val createdAt: LocalDateTime)
