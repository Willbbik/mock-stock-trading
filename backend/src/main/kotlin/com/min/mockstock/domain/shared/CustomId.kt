package com.min.mockstock.domain.shared

import org.hibernate.annotations.IdGeneratorType

@IdGeneratorType(ULIDGenerator::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class CustomId {
}