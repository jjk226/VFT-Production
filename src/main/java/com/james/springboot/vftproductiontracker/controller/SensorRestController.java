package com.james.springboot.vftproductiontracker.controller;

import com.james.springboot.vftproductiontracker.sensor.Sensor;
import com.james.springboot.vftproductiontracker.sensor.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/sensors")
public class SensorRestController {

    private SensorService sensorService;

    @Autowired
    public SensorRestController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("")
    public String sensorPage() {
        return "sensors";
    }

    @GetMapping("/all")
    public List<Sensor> all() {
        List<Sensor> sensors = this.sensorService.findAll();
        return sensors;
    }

    @GetMapping("/id/{id}")
    public Sensor findById(@PathVariable(value="id") int id) {
        return this.sensorService.findById(id);
    }

    @GetMapping("/serial/{serial}")
    public Sensor findBySerialNumber(@PathVariable(value="serial") int serial) {
        return this.sensorService.findBySerialNumber(serial);
    }

    @GetMapping("/type/{type}")
    public List<Sensor> findBySensorType(@PathVariable(value="type") String type) {
        return this.sensorService.findBySensorType(type);
    }

    @PostMapping("/add")
    public String add() {
        return "add-sensor";
    }


}
