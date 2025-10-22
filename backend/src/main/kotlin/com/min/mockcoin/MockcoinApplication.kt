package com.min.mockcoin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling

@ConfigurationPropertiesScan
@EnableCaching
@EnableScheduling
@SpringBootApplication
class MockcoinApplication
fun main(args: Array<String>) {
	runApplication<MockcoinApplication>(*args)
}