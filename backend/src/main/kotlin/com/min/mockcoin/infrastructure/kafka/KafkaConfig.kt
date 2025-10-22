//package com.min.mockcoin.infrastructure.kafka
//
//import org.apache.kafka.clients.consumer.ConsumerConfig
//import org.apache.kafka.clients.producer.ProducerConfig
//import org.apache.kafka.common.serialization.StringDeserializer
//import org.apache.kafka.common.serialization.StringSerializer
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.kafka.annotation.EnableKafka
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
//import org.springframework.kafka.core.*
//import org.springframework.kafka.support.serializer.JsonSerializer
//
//
//@EnableKafka
//@Configuration
//class KafkaConfig {
//
//    @Bean
//    fun consumerFactory(): ConsumerFactory<String, String> {
//        val configProps: MutableMap<String, Any> = HashMap()
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "mockcoin")
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
//        return DefaultKafkaConsumerFactory(configProps)
//    }
//
//    @Bean
//    fun producerFactory(): ProducerFactory<String, String> {
//        val configProps: MutableMap<String, Any> = HashMap()
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
//        return DefaultKafkaProducerFactory(configProps)
//    }
//
//    @Bean
//    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
//        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
//        factory.consumerFactory = consumerFactory()
//        return factory
//    }
//
//    @Bean
//    fun kafkaTemplate(): KafkaTemplate<String, String> {
//        return KafkaTemplate(producerFactory())
//    }
//
//}