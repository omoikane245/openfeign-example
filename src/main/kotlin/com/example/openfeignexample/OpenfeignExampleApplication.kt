package com.example.openfeignexample

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration
class OpenfeignExampleApplication

fun main(args: Array<String>) {
	runApplication<OpenfeignExampleApplication>(*args)
}
