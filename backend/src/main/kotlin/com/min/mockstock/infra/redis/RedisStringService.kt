package com.min.mockstock.infra.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisStringService(
    private val stringRedisTemplate: StringRedisTemplate

) {

    fun save(key: String, value: String) {
        stringRedisTemplate.opsForValue().set(key, value)
    }

    fun get(key: String): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }

    fun delete(key: String) {
        stringRedisTemplate.delete(key)
    }

}