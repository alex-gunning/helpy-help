package org.helpy.infrastructure

import org.helpy.domain.aggregate.accounts.Bank
import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.helpy.domain.ports.out.SaveUserAccountPort
import org.helpy.infrastructure.dao.Tables.GIFTEE
import org.helpy.infrastructure.dao.Tables.GIFTER
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountPersistenceAdapter(val dsl: DSLContext): LoadUserAccountPort, SaveUserAccountPort {
    override fun loadUserAccount(gifterId: GifterId): Gifter {
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
        return Giftee(
                gifteeId = GifteeId(UUID.randomUUID()),
                firstname = "Alex",
                surname = "Gunning",
                idNumber = null
       )
    }

    override fun saveUserAccount(gifter: Gifter): Boolean {
        dsl.insertInto(GIFTER)
                .set(GIFTER.FIRSTNAME, "")
                .set(GIFTER.SURNAME, "")
                .set(GIFTER.GIFTER_UUID, UUID.randomUUID().toString())
                .execute()
        return true
    }

    override fun saveUserAccount(giftee: Giftee): Boolean {
        dsl.insertInto(GIFTEE)
                .set(GIFTEE.FIRSTNAME, "")
                .set(GIFTEE.SURNAME, "")
                .set(GIFTEE.ID_NUMBER, "")
                .set(GIFTEE.GIFTEE_UUID, UUID.randomUUID().toString())
                .execute()
        return true
    }
}