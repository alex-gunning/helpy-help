package org.helpy.web.dtos

import org.helpy.domain.aggregate.accounts.BankAccount

data class GifterDto (val firstname: String,
                      val surname: String,
                      val bankAccount: BankAccount)
