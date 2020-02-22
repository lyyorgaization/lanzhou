package com.lucien.dap.framework.core.mq.kafka;

import lombok.Data;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@EnableKafka
@Component
@Configuration
public class KafkaProducerConfig {

    private KafkaConfig kafkaConfig;

    @Autowired
    public void setKafkaConfig(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrap_servers());
        if (kafkaConfig.getProducer() != null) {
            props.put(ProducerConfig.RETRIES_CONFIG, kafkaConfig.getProducer().getRetries());
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaConfig.getProducer().getBatch_size());
            props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaConfig.getProducer().getLinger_ms());
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaConfig.getProducer().getBuffer_memory());
        }
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}




