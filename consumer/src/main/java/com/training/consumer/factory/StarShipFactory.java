package com.training.consumer.factory;

import com.training.consumer.factory.entity.StarShip;
import com.training.consumer.kafka.dto.StarShipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarShipFactory {

    private final FighterFactory fighterFactory;

    @Autowired
    public StarShipFactory(FighterFactory fighterFactory) {
        this.fighterFactory = fighterFactory;
    }


    public StarShip build(StarShipDTO starShipDTO) {

        return StarShip.builder()
                .name(starShipDTO.getName())
                .build();

    }
}
