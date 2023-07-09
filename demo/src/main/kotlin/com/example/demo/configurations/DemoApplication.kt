package com.example.demo.configurations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.investment.security.configuration")
class DemoApplication
    fun main(args: Array<String>) {
        runApplication<DemoApplication>(*args)
}