package org.helpy.web.dtos

data class BankAccountDto(
    val provider: String,
    val branchCode: String,
    val accountNumber: String
)
