package com.min.mockstock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableCaching
@EnableScheduling
@EnableJpaAuditing
@ConfigurationPropertiesScan
@SpringBootApplication
class MockStockApplication
fun main(args: Array<String>) {
	runApplication<MockStockApplication>(*args)
}
