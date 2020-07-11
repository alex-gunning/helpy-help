package org.helpy.domain.ports.out

import org.helpy.domain.aggregate.transactions.ExternalTransactionIdentifier

interface ExternalTransaction {
    fun createTransaction(identifier: ExternalTransactionIdentifier): Any
}