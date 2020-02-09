package com.training.consumer.service;

import com.training.consumer.factory.StarShipFactory;
import com.training.consumer.factory.entity.StarShip;
import com.training.consumer.kafka.dto.StarShipDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainService {

    private final StarShipFactory starShipFactory;
    private final KafkaTemplate<Long, StarShipDTO> kafkaTemplate;


    @Autowired
    public MainService(StarShipFactory starShipFactory, KafkaTemplate<Long, StarShipDTO> kafkaTemplate) {
        this.starShipFactory = starShipFactory;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(StarShipDTO dto) {
        log.info("=> consumed {}", dto);
        StarShip starShip = starShipFactory.build(dto);
        log.info("insert to DB {}", starShip);
    }
}
