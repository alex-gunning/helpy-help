package org.helpy.domain.ports.out

import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.Gifter

interface SaveUserAccountPort {
   fun saveUserAccount(gifter: Gifter): Gifter
   fun saveUserAccount(giftee: Giftee): Giftee
}