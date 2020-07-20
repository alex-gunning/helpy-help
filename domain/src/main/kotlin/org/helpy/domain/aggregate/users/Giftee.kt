package org.helpy.domain.aggregate.users

import java.util.UUID

inline class GifteeId(val id: UUID)
data class Giftee(val gifteeId: GifteeId,
                  val firstname: String,
                  val surname: String,
                  val idNumber: String?)

