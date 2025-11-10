package com.min.mockstock.domain.shared

import com.github.f4b6a3.ulid.UlidCreator
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator

class ULIDGenerator : IdentifierGenerator {

    override fun generate(session: SharedSessionContractImplementor?, obj: Any?): Any {
        // ULID 생성 로직
        return UlidCreator.getMonotonicUlid().toString()
    }

}