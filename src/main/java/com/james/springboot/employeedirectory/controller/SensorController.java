package com.james.springboot.employeedirectory.controller;

import com.james.springboot.employeedirectory.sensor.Sensor;
import com.james.springboot.employeedirectory.sensor.SensorService;
import com.james.springboot.employeedirectory.sensor.SensorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sensors")
public class SensorController {

    private SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/all")
    public String all(Model model) {
        System.out.println(">> finding all sensors");
        List<Sensor> sensors = this.sensorService.findAll();
        System.out.println(">> adding model attribute");
        model.addAttribute("sensors", sensors);
        model.addAttribute("tempSensor", new Sensor());

        return "sensor/list-sensors";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        System.out.println(">> sensor id to update: " + id);
        Sensor tempSensor = this.sensorService.findById(id);
        model.addAttribute("tempSensor", tempSensor);

        return "sensor/update-sensor-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("tempSensor") @Valid Sensor sensor, Error error, Model model) {
        int tempSerialNumber = sensor.getSerialNumber();
        if (this.sensorService.existsBySerialNumber(tempSerialNumber)) {
            System.out.println(">> WARNING! DUPLICATE PART NUMBER");
            //return "error";
            this.sensorService.save(sensor);
        } else {
            this.sensorService.save(sensor);
        }

        return "redirect:/sensors/all";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        this.sensorService.deleteById(id);

        return "redirect:/sensors/all";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Sensor sensor = new Sensor();

        model.addAttribute("tempSensor", sensor);
        model.addAttribute("sensorTypes", SensorType.values());

        return "sensor/add-sensor";
    }

    @PostMapping("/new")
    public String addNewSensor(@ModelAttribute("tempSensor") @Valid Sensor sensor, Error error, Model model) {
        if (error.equals(true)) {
            return "error";
        } else if (sensorService.findBySerialNumber(sensor.getSerialNumber()) != null) {
            System.out.println(">> Serial Number already exists.");
            return "error";
        } else {
            this.sensorService.save(sensor);
        }

        return "redirect:/sensors/all";
    }


    @GetMapping("/find")
    public String findBySerialNumber(@ModelAttribute("tempSensor") Sensor sensor, Model model, Error error) {
        System.out.println(">> serial number to retrieve: " + sensor.getSerialNumber());

        List<Sensor> sensors = new ArrayList<>();
        Sensor tempSensor = this.sensorService.findBySerialNumber(sensor.getSerialNumber());
        sensors.add(tempSensor);
        model.addAttribute("sensors", sensors);

        return "sensor/list-sensors";
    }

}
