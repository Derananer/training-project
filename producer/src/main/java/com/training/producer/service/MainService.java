package com.training.producer.service;

import com.training.producer.kafka.dto.StarShipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    private final KafkaTemplate<Long, StarShipDTO> kafkaTemplate;

    @Autowired
    public MainService(KafkaTemplate<Long, StarShipDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(int count){
        for (int i = 0; i < count; i++) {
            kafkaTemplate.send("server.starship",new StarShipDTO("ship" + i, "type"));
        }
    }
}
