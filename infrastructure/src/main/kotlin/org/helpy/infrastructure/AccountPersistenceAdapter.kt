package org.helpy.infrastructure

import arrow.core.Either
import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.domain.aggregate.users.Giftee
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.errors.LoadUserAccountError
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.helpy.domain.ports.out.SaveUserAccountPort
import org.helpy.infrastructure.dao.Tables.BANK_ACCOUNT
import org.helpy.infrastructure.dao.Tables.GIFTEE
import org.helpy.infrastructure.dao.Tables.GIFTER
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountPersistenceAdapter(val dsl: DSLContext) : LoadUserAccountPort, SaveUserAccountPort {
    override fun loadUserAccount(gifterId: GifterId): Either<LoadUserAccountError, Gifter> {
        val result =
                dsl.select(
                    GIFTER.FIRSTNAME, GIFTER.SURNAME,
                    BANK_ACCOUNT.ACCOUNT_NUMBER, BANK_ACCOUNT.BRANCH_CODE, BANK_ACCOUNT.PROVIDER)
                .from(GIFTER)
                .leftJoin(BANK_ACCOUNT)
                .on(GIFTER.BANK_ACCOUNT_ID.equal(BANK_ACCOUNT.ACCOUNT_ID))
                .where(GIFTER.GIFTER_UUID.equal(gifterId.id.toString()))
                .fetch()
        return Either.cond(result.isNotEmpty,
                {
                    Gifter(firstname = result.getValue(0, GIFTER.FIRSTNAME),
                           surname = result.getValue(0, GIFTER.SURNAME),
                           gifterId = gifterId,
                           bankAccount = BankAccount(
                                   accountId = UUID.randomUUID(),
                                   provider = result.getValue(0, BANK_ACCOUNT.PROVIDER).name,
                                   branchCode = result.getValue(0, BANK_ACCOUNT.BRANCH_CODE),
                                   accountNumber = result.getValue(0, BANK_ACCOUNT.ACCOUNT_NUMBER)
                           ))
                },
                { LoadUserAccountError.userAccountNotFound }
        )
    }

    override fun loadUserAccount(gifteeId: GifteeId): Either<LoadUserAccountError, Giftee> {
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