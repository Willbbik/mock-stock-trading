package com.min.mockstock.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.data.redis")
data class RedisProperties(
        val host: String,
        val port: Int,
        val ttl: Long
)