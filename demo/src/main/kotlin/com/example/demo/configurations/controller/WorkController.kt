package com.example.demo.configurations.controller

import com.example.demo.configurations.domain.Employee
import com.example.demo.configurations.service.WorkService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class WorkController(private val workService: WorkService) {

    @GetMapping("/mostExpensive/{count}")
    fun getMostExpensiveEmployees(@PathVariable("count") count: Int): List<Employee> {
        return workService.getMostExpensiveEmployees(count)
    }

    @GetMapping("/department/salary")
    fun getDepartmentSalary(): Map<String, Int> {
        return workService.getSalaryByDepartment();
    }

}