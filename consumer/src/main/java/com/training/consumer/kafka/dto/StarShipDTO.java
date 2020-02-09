package com.training.consumer.kafka.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class StarShipDTO extends AbstractKafkaMessage {
    private String name;
    private String type;
}
