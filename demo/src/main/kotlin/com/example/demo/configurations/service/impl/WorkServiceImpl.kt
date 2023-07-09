package com.example.demo.configurations.service.impl

import com.example.demo.configurations.domain.Department
import com.example.demo.configurations.domain.Employee
import com.example.demo.configurations.service.WorkService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class WorkServiceImpl : WorkService {

    private val employees = getEmployees();

    override fun getMostExpensiveEmployees(count: Int): List<Employee> {
     return employees.sortedBy { it.salary }.subList(employees.size - count, employees.size)
    }

    override fun getSalaryByDepartment(): Map<String, Int> {
        return employees
            .groupBy ({ it.department!! }, {it.salary})
            .mapKeys { (department, salaries) -> department.name }
            .mapValues { (department, salaries) -> salaries.sumOf { it.toInt() } }
    }

    private fun getEmployees(): MutableList<Employee> {
        val finance = Department("Finance")
        val bookkeeping = Department("Bookkeeping")

        val employee1 = Employee("John", "Galt")
        employee1.salary = BigDecimal("15500")
        employee1.department = finance
        val employee2 = Employee("Henk", "Rearden")
        employee2.salary = BigDecimal("14500")
        employee2.department = finance


        val employee3 = Employee("Daisy", "Stone")
        employee3.salary = BigDecimal("16500")
        employee3.department = bookkeeping
        val employee4 = Employee("Bill", "Miligan")
        employee4.salary = BigDecimal("21500")
        employee4.department = bookkeeping


        bookkeeping.employees = mutableListOf(employee3, employee4)

        return mutableListOf(employee1, employee2, employee3, employee4)
    }
}
