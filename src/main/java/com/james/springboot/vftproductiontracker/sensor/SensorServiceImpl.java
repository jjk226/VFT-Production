package com.james.springboot.vftproductiontracker.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SensorServiceImpl implements SensorService {

    private SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

//    public SensorServiceImpl() {
//
//    }
    @Override
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor findById(int id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        Sensor returnSensor = sensor.get();
        return returnSensor;
    }


    @Override
    public Sensor findBySerialNumber(int serial) {
        return this.sensorRepository.findBySerialNumber(serial);
    }

    @Override
    public boolean existsBySerialNumber(int serial) {
        return this.sensorRepository.existsBySerialNumber(serial);
    }

    @Override
    public List<Sensor> findBySensorType(String type) {
        return this.sensorRepository.findBySensorType(type);
    }

    @Override
    public void save(Sensor sensor) {
        this.sensorRepository.save(sensor);
    }

    @Override
    public void deleteById(int id) {
        this.sensorRepository.deleteById(id);
    }


}
