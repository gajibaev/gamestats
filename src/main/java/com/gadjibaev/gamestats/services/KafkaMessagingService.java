package com.gadjibaev.gamestats.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaMessagingService {
    private static final String topicCreateOrder = "${topic.test}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    @Transactional
    @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId)
    public String sendMessage(String message) {
        log.info("Message was send {}", message);
        return message;
    }

}