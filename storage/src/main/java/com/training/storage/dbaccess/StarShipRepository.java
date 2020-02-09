package com.training.storage.dbaccess;

import com.training.storage.dbaccess.entity.StarShip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StarShipRepository extends CrudRepository<StarShip, Long> {

    List<StarShip> findByName(String name);

    @Override
    Optional<StarShip> findById(Long aLong);
}
