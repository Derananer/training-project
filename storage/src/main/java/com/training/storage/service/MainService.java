package com.training.storage.service;

import com.training.storage.dbaccess.StarShipRepository;
import com.training.storage.dbaccess.entity.StarShip;
import com.training.storage.kafka.dto.MessageForStore;
import javafx.beans.Observable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MainService {

    private final StarShipRepository starShipRepository;

    @Autowired
    public MainService(StarShipRepository starShipRepository) {
        this.starShipRepository = starShipRepository;
    }

    @KafkaListener(id = "StarshipStoring", topics = {"server.starships-store"}, containerFactory = "singleFactory")
    public void consume(MessageForStore dto) {
        log.info("=> consumed {}", dto);
        Optional<StarShip> starShip = starShipRepository.findById(dto.getStarshipId());
        log.info("Get from DB " + starShip.isPresent());
        starShip.ifPresent((s) -> log.info("Get from DB " + s.toString()));
    }
}
