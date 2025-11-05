package com.min.mockstock.common.util

import java.nio.charset.StandardCharsets
import java.util.*

object Base64Utils {

    /**
     * 바이트 배열을 Base64 문자열로 인코딩합니다.
     */
    fun encode(data: ByteArray): String {
        return Base64.getEncoder().encodeToString(data)
    }

    /**
     * 문자열을 Base64 문자열로 인코딩합니다. (UTF-8 인코딩 적용)
     */
    fun encode(data: String): String {
        return Base64.getEncoder().encodeToString(data.toByteArray(StandardCharsets.UTF_8))
    }

    /**
     * Base64 문자열을 바이트 배열로 디코딩합니다.
     */
    fun decode(base64Text: String): ByteArray {
        return Base64.getDecoder().decode(base64Text)
    }

    /**
     * Base64 문자열을 일반 문자열로 디코딩합니다. (UTF-8 디코딩 적용)
     */
    fun decodeToString(base64Text: String): String {
        val decodedBytes = Base64.getDecoder().decode(base64Text)
        return String(decodedBytes, StandardCharsets.UTF_8)
    }

}