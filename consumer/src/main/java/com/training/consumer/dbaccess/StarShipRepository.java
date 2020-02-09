package com.training.consumer.dbaccess;

import com.training.consumer.dbaccess.entity.StarShip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StarShipRepository extends CrudRepository<StarShip, Long> {

    List<StarShip> findByName(String name);

    @Override
    <S extends StarShip> S save(S s);
}
