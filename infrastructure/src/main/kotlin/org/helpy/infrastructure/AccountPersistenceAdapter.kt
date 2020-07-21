package org.helpy.infrastructure

import org.helpy.domain.aggregate.accounts.Bank
import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.helpy.infrastructure.dao.Tables.PENDING_GIFTS
import org.helpy.infrastructure.dao.tables.PendingGifts
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountPersistenceAdapter(val dsl: DSLContext): LoadUserAccountPort {
    override fun loadUserAccount(gifterId: GifterId): Gifter {
//        dsl.insertInto(PENDING_GIFTS)
//                .set(PENDING_GIFTS.GIFTEE_ID, 3)
//                .set(PENDING_GIFTS.GIFTER_ID, 3)
//                .set(PENDING_GIFTS.PENDING_GIFT_UUID, "AllYourGUIDAreBelongToUs")
//                .execute()
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