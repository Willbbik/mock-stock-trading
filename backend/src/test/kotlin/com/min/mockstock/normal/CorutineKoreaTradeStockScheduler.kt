package com.min.mockstock.normal

import com.min.mockstock.domain.shared.StockInfo
import com.min.mockstock.domain.shared.Stocks
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Test

class CorutineKoreaTradeStockScheduler {


    var isActive = false
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val stocks: List<StockInfo> = Stocks.list

    @Test
    fun `suspend는 쓰레드를 막지 않는다`() = runBlocking {
        println("시작 - 쓰레드: ${Thread.currentThread().name}")

        val job = launch {
            println("코루틴 시작 - 쓰레드: ${Thread.currentThread().name}")
            delay(500)
            println("코루틴 재개 - 쓰레드: ${Thread.currentThread().name}")
        }

        println("runBlocking은 여전히 실행 중 ${Thread.currentThread().name}")
        job.join()
    }

    @Test
    fun `runBlocking은 쓰레드를 멈춘다`() {
        println("A - 쓰레드: ${Thread.currentThread().name}")

        runBlocking {
            println("runBlocking 내부 - 쓰레드: ${Thread.currentThread().name}")
            Thread.sleep(500) // 강제 블로킹
            println("runBlocking 완료")
        }

        println("B - runBlocking 이후")
    }

    @Test
    fun `launch는 부모를 기다리지 않는다`() = runBlocking {
        println("부모 시작")

        launch {
            println("자식 시작")
            delay(300)
            println("자식 종료")
        }

        println("부모 종료 (자식 기다리지 않음)")
        delay(500) // 자식 로그 보기 위해 잠깐 대기
    }

    @Test
    fun `async는 값을 반환한다`() = runBlocking {
        val jobs = listOf(
            async { delay(300); "A" },
            async { delay(100); "B" },
            async { delay(200); "C" }
        )

        println("결과 전: 아직 아무것도 안 기다림")
        val results = jobs.awaitAll()
        println("결과: $results")
    }

    private var count = 0
    private val mutex = Mutex()

    @Test
    fun `Mutex 없으면 값이 꼬인다`() = runBlocking {
        repeat(1000) {
            launch {
                val temp = count
                delay(1)
                count = temp + 1
            }
        }
        delay(2000)
        println("결과 (꼬임): $count") // 1000 안 나옴
    }

    @Test
    fun `Mutex 사용하면 값이 정확`() = runBlocking {
        repeat(1000) {
            launch {
                mutex.withLock {
                    val temp = count
                    delay(1)
                    count = temp + 1
                }
            }
        }
        delay(2000)
        println("결과 (정확): $count") // 항상 1000
    }

    @Test
    fun `Mutex 테스트`() = {
        scope.launch {
            while(isActive) {
                try {

                    stocks.chunked(20).forEachIndexed { idx, batch ->
                        println("🚀 [Batch ${idx + 1}] ${batch.size}개 종목 조회 시작")

                        val responses = batch.map { stock ->
                            async {
                                count = count + 1
                            }
                        }.awaitAll()

                        delay(1000) // 1초 간격
                    }

                } catch (e: Exception) {
                    println("Error occurred: ${e.message}. Continuing...")
                }
            }
        }
    }

//    @Test
//    fun test() {
//        scope.launch {
//            stocks.chunked(20).forEachIndexed { idx, batch ->
//                println("🚀 [Batch ${idx + 1}] ${batch.size}개 종목 조회 시작")
//                batch.map { stock ->
//                    println(getCode())
//                }
//            }
//        }
//    }

    fun getCode(): String {
        return getIsActive()
    }

    fun getIsActive(): String {

        if(!isActive) {
            println("is Non Active")
            isActive = true
            return "NO"
        }

        println("is Active")
        return "OK"
    }

}