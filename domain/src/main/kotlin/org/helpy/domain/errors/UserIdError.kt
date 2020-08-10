package org.helpy.domain.errors

sealed class UserIdError {
    object createIdError: UserIdError()
}