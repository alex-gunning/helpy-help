package org.helpy.web.validations

import arrow.core.Tuple2
import arrow.core.Tuple3
import arrow.core.Tuple4
import arrow.core.Validated
import arrow.core.extensions.validated.bitraverse.bimap
import org.helpy.web.dtos.GifterDto
import org.springframework.stereotype.Component

@Component
object GifterDtoRequestValidation {
    fun validate(gifterDto: GifterDto): Validated<List<String>, GifterDto> =
        (ValidationStrategy accumulateErrors {
            using {
                map(
                    test(gifterDto, { it.firstname.isNotEmpty() }, "firstname is missing"),
                    test(gifterDto, { it.surname.isNotEmpty() }, "surname is missing"),
                    test(gifterDto, { it.bankAccount.accountNumber.isNotEmpty() }, "bank account number is missing"),
                    test(gifterDto, { it.bankAccount.provider.isNotEmpty() }, "bank provider missing"),
                    test(gifterDto, { it.bankAccount.branchCode.isNotEmpty() }, "bank branch code is missing")
                ) { gifterDto }
            }
        }).bimap({ it.toList() }, { it })
}
