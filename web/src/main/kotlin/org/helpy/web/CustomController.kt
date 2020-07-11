package org.helpy.web

import org.helpy.domain.CreatePendingDepositGiftUseCase
import org.helpy.domain.SendPendingDepositGiftAccountCommand
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.aggregate.utils.Money
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
class CustomController(
       val sendPendingGiftService: CreatePendingDepositGiftUseCase
) {

    @GetMapping("/")
    fun bleh(): String {
        return "blehBleh"
    }

    @GetMapping("/customer")
    fun customer(): String {
        val command = SendPendingDepositGiftAccountCommand(
                amount = Money(amount = BigDecimal.TEN),
                gifterId = GifterId(UUID.randomUUID()),
                gifteeId = GifteeId(UUID.randomUUID())
        )
        val temporaryDepositAccount = sendPendingGiftService.createPendingMoneyGiftAccount(command)
        return temporaryDepositAccount.toString() ?: "Could not create transaction!"
    }
}
