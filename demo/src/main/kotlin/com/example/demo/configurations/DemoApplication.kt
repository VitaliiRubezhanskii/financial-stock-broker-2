package com.example.demo.configurations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication
    fun main(args: Array<String>) {
        runApplication<DemoApplication>(*args)
}