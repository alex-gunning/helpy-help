package org.helpy.web

import arrow.core.Either
import arrow.core.extensions.either.foldable.foldLeft
import arrow.core.extensions.fx
import arrow.core.getOrElse
import arrow.core.getOrHandle
import arrow.core.left
import arrow.core.right
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
import kotlin.IllegalArgumentException

@RestController
@RequestMapping("customer")
class CustomController(
        val sendPendingGiftService: CreatePendingDepositGiftUseCase,
        val loadUserAccount: LoadUserAccountPort,
        val saveUserAccount: SaveUserAccountPort
) {

    fun parseUUID(uuid: String): Either<Throwable, UUID> =
        try {
            UUID.fromString(uuid).right()
        } catch (e: IllegalArgumentException) {
            e.left()
        }

    @GetMapping("/gifter/{gifterid}")
    fun fetchGifter(@PathVariable(name = "gifterid") id: String): Gifter {
        val eitherGifter = parseUUID(id).map { GifterId(it) }
        return Either.fx<Throwable, Gifter> {
            val (id) = eitherGifter
            loadUserAccount.loadUserAccount(id)
        }.getOrHandle {
            when(it) {
                // Domain errors
                is IllegalArgumentException -> throw Error("Bad! Will be caught by ControllerAdvice")
                is RuntimeException -> throw Error("Good! Cannot save!") // <-- Wrap in either
                else -> throw Error("Wtf!")
            }
        }

//        Either.fx<Throwable, GifterId> {
//           val (id) = eitherGifter
//           val (gifterId) = loadUserAccount.loadUserAccount(id)
//           gifterId
//        }
//        return when(eitherGifter) {
//            is Either.Left -> throw Error("BAD!")
//            is Either.Right -> loadUserAccount.loadUserAccount(eitherGifter.b)
//        }
    }

    @PostMapping
    fun createGifter(@RequestBody(required = true) Gifter: GifterDto): Gifter {
        TODO("Create a gifter")
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
