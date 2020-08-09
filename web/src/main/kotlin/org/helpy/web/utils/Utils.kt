package org.helpy.web.utils

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.util.UUID

object Utils {
    fun parseUUID(uuid: String): Either<Throwable, UUID> =
            try {
                UUID.fromString(uuid).right()
            } catch (e: IllegalArgumentException) {
                e.left()
            }
}