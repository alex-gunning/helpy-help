package org.helpy.infrastructure

import org.helpy.domain.aggregate.accounts.Bank
import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountPersistenceAdapter: LoadUserAccountPort {
    override fun loadUserAccount(gifterId: GifterId): Gifter {
//        TODO("Load Gifter account via JPA")
        return Gifter(
                gifterId = GifterId(UUID.randomUUID()),
                firstname = "Alex",
                surname = "Gunning",
                bankAccount = BankAccount(
                        accountId = UUID.randomUUID(),
                        provider = Bank.CAPITEC,
                        branch = "Wynberg",
                        accountNumber = "123456"
                )
        )
    }

    override fun loadUserAccount(gifteeId: GifteeId): Giftee {
//        TODO("Load Giftee account via JPA")
        return Giftee(
                gifteeId = GifteeId(UUID.randomUUID()),
                firstname = "Alex",
                surname = "Gunning",
                idNumber = null
       )
    }

}