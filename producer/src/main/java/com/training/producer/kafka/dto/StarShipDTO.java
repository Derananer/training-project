package com.training.producer.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StarShipDTO extends AbstractProduct {
    private String name;
    private String type;
}
