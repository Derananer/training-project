package com.training.consumer.service;

import com.training.consumer.dbaccess.StarShipRepository;
import com.training.consumer.factory.StarShipFactory;
import com.training.consumer.dbaccess.entity.StarShip;
import com.training.consumer.kafka.dto.MessageForStore;
import com.training.consumer.kafka.dto.StarShipDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class MainService {

    private final StarShipFactory starShipFactory;
    private final KafkaTemplate<Long, MessageForStore> kafkaTemplate;
    private final StarShipRepository starShipRepository;
    private AtomicInteger builtShipsCount;

    @Autowired
    public MainService(StarShipFactory starShipFactory, KafkaTemplate<Long, MessageForStore> kafkaTemplate, StarShipRepository starShipRepository) {
        this.starShipFactory = starShipFactory;
        this.kafkaTemplate = kafkaTemplate;
        this.starShipRepository = starShipRepository;
        builtShipsCount = new AtomicInteger();
    }

    @KafkaListener(topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(StarShipDTO dto) {
        log.info("=> consumed {}", dto);
        StarShip starShip = starShipRepository.save(starShipFactory.build(dto));
        log.info("insert to DB {}", starShip);
        kafkaTemplate.send("server.starships-store", new MessageForStore(starShip.getId()));
        builtShipsCount.incrementAndGet();
        log.info("COUNT " + builtShipsCount.get());
    }
}
