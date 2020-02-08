package com.training.consumer.factory.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StarShip {

    private Long id;
    private String name;
    private List<Fighter> fighters;

}
