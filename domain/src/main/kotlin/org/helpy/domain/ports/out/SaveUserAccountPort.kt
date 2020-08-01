package org.helpy.domain.ports.out

import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.Gifter

interface SaveUserAccountPort {
   fun saveUserAccount(gifter: Gifter): Boolean
   fun saveUserAccount(giftee: Giftee): Boolean
}