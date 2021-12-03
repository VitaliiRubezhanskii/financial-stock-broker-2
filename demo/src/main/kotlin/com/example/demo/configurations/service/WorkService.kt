package com.example.demo.configurations.service

import com.example.demo.configurations.domain.Employee

interface WorkService {

   fun getMostExpensiveEmployees(count: Int): List<Employee>

   fun getSalaryByDepartment(): Map<String, Int>
}
