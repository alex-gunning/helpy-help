package org.helpy.web.validations

import arrow.Kind
import arrow.core.Either
import arrow.core.EitherPartialOf
import arrow.core.Nel
import arrow.core.NonEmptyList
import arrow.core.Validated
import arrow.core.ValidatedPartialOf
import arrow.core.extensions.either.applicativeError.applicativeError
import arrow.core.extensions.nonemptylist.semigroup.semigroup
import arrow.core.extensions.validated.applicativeError.applicativeError
import arrow.core.nel
import arrow.typeclasses.ApplicativeError

typealias ValidationError = String

sealed class ValidationStrategy<F>(A: ApplicativeError<F, Nel<ValidationError>>) :
    ApplicativeError<F, Nel<ValidationError>> by A {

    fun <T> using(validator: () -> Kind<F, T>): Kind<F, T> =
        validator().handleErrorWith(::raiseError)

    private fun <T> nel(error: ValidationError): Kind<F, T> = raiseError(error.nel())

    // pre-defined validators
    fun <T> test(
        t: T,
        validator: (
            t: T,
            pass: (c: T) -> Kind<F, T>,
            fail: (e: ValidationError) -> Kind<F, T>
        )
        -> Kind<F, T>
    ): Kind<F, T> = validator(t, ::just, ::nel)

    // inline tests
    fun <T> test(t: T, predicate: (t: T) -> Boolean, error: ValidationError) =
        if (predicate(t)) just(t)
        else nel(error)

    object ErrorAccumulationStrategy :
        ValidationStrategy<ValidatedPartialOf<Nel<ValidationError>>>(Validated.applicativeError(NonEmptyList.semigroup()))

    object FailFastStrategy :
        ValidationStrategy<EitherPartialOf<Nel<ValidationError>>>(Either.applicativeError())

    companion object {
        infix fun <A> failFast(f: FailFastStrategy.() -> A): A = f(FailFastStrategy)
        infix fun <A> accumulateErrors(f: ErrorAccumulationStrategy.() -> A): A = f(ErrorAccumulationStrategy)
    }
}

