package org.helpy.domain.aggregate.users

import java.util.UUID

interface Photo {
    fun getData()
}
data class Giftee(val gifteeId: UUID,
                  val firstname: String,
                  val surname: String,
                  val idNumber: String?,
                  val photo: Photo)

