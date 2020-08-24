package org.helpy.web.mappers

import org.helpy.domain.aggregate.accounts.BankAccount
import org.helpy.web.dtos.BankAccountDto
import java.util.*

class BankAccountMapper {
    companion object {
        fun fromDTO(dto: BankAccountDto): BankAccount =
            BankAccount(
                accountId = null,
                accountNumber = dto.accountNumber,
                branchCode = dto.branchCode,
                provider = dto.provider
            )
    }
}