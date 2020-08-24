package org.helpy.web.mappers

import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.web.dtos.GifterDto
import java.util.*

class GifterMapper {
    companion object {
        fun fromDTO(dto: GifterDto): Gifter =
            Gifter(gifterId = null,
                bankAccount = BankAccountMapper.fromDTO(dto.bankAccount),
                firstname = dto.firstname,
                surname = dto.surname
            )
    }
}