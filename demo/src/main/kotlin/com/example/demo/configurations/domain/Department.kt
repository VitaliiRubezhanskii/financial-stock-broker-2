package com.example.demo.configurations.domain

data class Department(val name: String) {
    var employees: MutableList<Employee> = mutableListOf()
}