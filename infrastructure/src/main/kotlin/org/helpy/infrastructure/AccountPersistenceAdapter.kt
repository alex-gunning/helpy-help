package org.helpy.infrastructure

import arrow.core.Either
import arrow.core.right
import org.helpy.domain.aggregate.accounts.Bank
import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.exceptions.LoadUserException
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.helpy.domain.ports.out.SaveUserAccountPort
import org.helpy.infrastructure.dao.Tables.GIFTEE
import org.helpy.infrastructure.dao.Tables.GIFTER
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountPersistenceAdapter(val dsl: DSLContext) : LoadUserAccountPort, SaveUserAccountPort {
    override fun loadUserAccount(gifterId: GifterId): Either<Throwable, Gifter> {
        val result = dsl.select(GIFTER.FIRSTNAME, GIFTER.SURNAME)
                .from(GIFTER)
                .where(GIFTER.GIFTER_UUID.equal(gifterId.id.toString()))
                .fetch()
        return if(result.isNotEmpty) {
            Either.right(Gifter(firstname = result.getValue(0, GIFTER.FIRSTNAME),
                    surname = result.getValue(0, GIFTER.SURNAME),
                    gifterId = gifterId,
                    bankAccount = BankAccount(
                            accountId = UUID.randomUUID(),
                            provider = Bank.CAPITEC,
                            branch = "Wynberg",
                            accountNumber = "123456"
                    )))
        } else {
            Either.left(LoadUserException("Could not find user"))
        }
    }

    override fun loadUserAccount(gifteeId: GifteeId): Either<Throwable, Giftee> {
        return throw RuntimeException("Cannot retrieve")
//        return Giftee(
//                gifteeId = GifteeId(UUID.randomUUID()),
//                firstname = "Alex",
//                surname = "Gunning",
//                idNumber = null
//       )
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