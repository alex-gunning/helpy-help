package org.helpy.web

import org.helpy.domain.ports.`in`.usecases.CreatePendingDepositGiftUseCase
import org.helpy.domain.ports.`in`.usecases.SendPendingDepositGiftAccountCommand
import org.helpy.domain.aggregate.users.GifteeId
import org.helpy.domain.aggregate.users.Gifter
import org.helpy.domain.aggregate.users.GifterId
import org.helpy.domain.aggregate.utils.Money
import org.helpy.domain.ports.out.LoadUserAccountPort
import org.helpy.domain.ports.out.SaveUserAccountPort
import org.helpy.web.dtos.GifterDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
@RequestMapping("customer")
class CustomController(
       val sendPendingGiftService: CreatePendingDepositGiftUseCase,
       val loadUserAccount: LoadUserAccountPort,
       val saveUserAccount: SaveUserAccountPort
) {

    @GetMapping("/gifter/{gifterid}")
    fun fetchGifter(@PathVariable(name = "gifterid") gifterId: String): Gifter {
        return loadUserAccount.loadUserAccount(GifterId(UUID.fromString(gifterId)))
    }

    @PostMapping
    fun createGifter(@RequestBody(required = true) Gifter: GifterDto): Gifter {
        TODO("Create a gifter")
    }

    @GetMapping( "/customer" )
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
