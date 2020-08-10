package org.helpy.web.utils

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import org.helpy.domain.errors.UserIdError
import java.util.UUID

object Utils {
    fun parseUUID(uuid: String): Either<UserIdError, UUID> =
            try {
                UUID.fromString(uuid).right()
            } catch (e: IllegalArgumentException) {
                UserIdError.createIdError.left()
            }
}