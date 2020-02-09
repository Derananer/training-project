package com.training.consumer.factory;

import com.training.consumer.dbaccess.entity.StarShip;
import com.training.consumer.kafka.dto.StarShipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StarShipFactory {

    @Value("${custom.sleeptime}")
    private int sleeptime;

    private final FighterFactory fighterFactory;

    @Autowired
    public StarShipFactory(FighterFactory fighterFactory) {
        this.fighterFactory = fighterFactory;
    }


    public StarShip build(StarShipDTO starShipDTO) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return StarShip.builder()
                .name(starShipDTO.getName())
                .build();

    }
}
