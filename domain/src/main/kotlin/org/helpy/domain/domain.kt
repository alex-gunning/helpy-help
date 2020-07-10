package org.helpy.domain

data class Customer(val name: String, val surname: String, val idNumber: String)
interface CustomerService {
   fun save(customer: Customer): Customer
   fun fetch(customerId: Int): Customer
}
