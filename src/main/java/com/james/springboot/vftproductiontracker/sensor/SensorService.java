package com.james.springboot.vftproductiontracker.sensor;

import java.util.List;

public interface SensorService {

    public List<Sensor> findAll();

    public Sensor findById(int id);

    public Sensor findBySerialNumber(int serial);

    public boolean existsBySerialNumber(int serial);

    public List<Sensor> findBySensorType(String type);

    public void save(Sensor sensor);

    public void deleteById(int id);




}
