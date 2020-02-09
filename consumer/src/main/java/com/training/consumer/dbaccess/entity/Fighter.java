package com.training.consumer.dbaccess.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fighter {
    private Long id;
    private String name;
}
