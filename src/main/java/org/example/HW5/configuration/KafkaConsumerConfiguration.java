package org.example.HW5.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.HW5.service.event.MessageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConsumerConfiguration {


    @Bean
    public ConsumerFactory<String, MessageEvent> consumerFactory(){

        Map<String,Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "message-topic-handler");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        properties.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, MessageEvent.class.getName());
        properties.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
        properties.put(JacksonJsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        return new  DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,MessageEvent> containerFactory(
            ConsumerFactory<String,MessageEvent> consumerFactory
    ){
        var containerFactory = new ConcurrentKafkaListenerContainerFactory<String,MessageEvent>();
        containerFactory.setConcurrency(1);
        containerFactory.setConsumerFactory(consumerFactory);
        return containerFactory;
    }
}
