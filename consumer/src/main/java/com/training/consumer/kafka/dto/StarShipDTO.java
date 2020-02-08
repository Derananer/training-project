package com.training.consumer.kafka.dto;

import lombok.Data;

@Data
public class StarShipDTO extends AbstractProduct {
    private String name;
    private String type;
}
