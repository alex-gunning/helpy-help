package org.helpy.domain.errors

sealed class LoadUserAccountError {
    object userAccountNotFound: LoadUserAccountError()
}
