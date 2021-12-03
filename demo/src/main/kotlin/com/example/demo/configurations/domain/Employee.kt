package com.example.demo.configurations.domain

import java.math.BigDecimal

data class Employee(val firstName: String, val lastName: String) {
    var age: Int = 0
    var department: Department? = null
    var salary: BigDecimal = BigDecimal.ZERO

}