//import com.min.mockcoin.domain.redis.upbit.Market
//import com.min.mockcoin.domain.redis.upbit.UpbitMarketRepositoryRedis
//import com.min.mockcoin.domain.shared.MarketType
//import com.min.mockcoin.infrastructure.upbit.UpbitManager
//import com.min.mockcoin.scheduler.UpbitScheduler
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Nested
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.verify
//import org.springframework.kafka.core.KafkaTemplate
//
//class UpbitSchedulerTest {
//
//    private lateinit var upbitManager: UpbitManager
//    private lateinit var upbitMarketRepositoryRedis: UpbitMarketRepositoryRedis
//    private lateinit var kafkaTemplate: KafkaTemplate<String, String>
//    private lateinit var upbitScheduler: UpbitScheduler
//
//    @BeforeEach
//    fun setUp() {
//        upbitManager = mock()
//        upbitMarketRepositoryRedis = mockk(relaxed = true)
//        kafkaTemplate = mockk(relaxed = true)
//    }
//
//    @Nested
//    @DisplayName("updateMarkets")
//    inner class UpdateMarkets {
//
//        @Test
//        @DisplayName("should save grouped market data by type")
//        fun shouldSaveGroupedMarketDataByType() {
//            val mockMarkets = listOf(
//                    Market(MarketType.KRW, "KRW-BTC"),
//                    Market(MarketType.KRW, "KRW-ETH"),
//                    Market(MarketType.BTC, "BTC-ETH"),
//                    Market(MarketType.USDT, "USDT-BTC")
//            )
//            every { upbitManager.getMarkets() } returns mockMarkets
//
//            upbitScheduler.updateMarkets()
//
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.KRW, "KRW-BTC, KRW-ETH")) }
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.BTC, "BTC-ETH")) }
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.USDT, "USDT-BTC")) }
//        }
//
//        @Test
//        @DisplayName("should handle empty market list gracefully")
//        fun shouldHandleEmptyMarketListGracefully() {
//            every { upbitManager.getMarkets() } returns emptyList()
//
//            upbitScheduler.updateMarkets()
//
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.KRW, "")) }
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.BTC, "")) }
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.USDT, "")) }
//        }
//
//        @Test
//        @DisplayName("should handle null market values gracefully")
//        fun shouldHandleNullMarketValuesGracefully() {
//            val mockMarkets = listOf(
//                    Market(MarketType.KRW, null),
//                    Market(MarketType.BTC, null),
//                    Market(MarketType.USDT, null)
//            )
//            every { upbitManager.getMarkets() } returns mockMarkets
//
//            upbitScheduler.updateMarkets()
//
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.KRW, "")) }
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.BTC, "")) }
//            verify { upbitMarketRepositoryRedis.save(Market(MarketType.USDT, "")) }
//        }
//    }
//}