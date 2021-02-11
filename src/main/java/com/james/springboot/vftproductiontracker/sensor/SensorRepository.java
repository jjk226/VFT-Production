package com.james.springboot.vftproductiontracker.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    public Sensor findBySerialNumber(int serial);

    public boolean existsBySerialNumber(int serial);

    public List<Sensor> findBySensorType(String type);



}
