package org.helpy.infrastructure

import org.helpy.domain.Customer
import org.helpy.domain.CustomerService
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class ORMAdapter: CustomerService {
    override fun save(customer: Customer): Customer {
        return Customer("Alex", "Gunning", "091209")
    }
    override fun fetch(customerId: Int): Customer {
        return Customer("Alex", "Gunning", "091209")
    }
}