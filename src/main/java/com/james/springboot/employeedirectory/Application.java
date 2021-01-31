package com.james.springboot.employeedirectory;

import com.james.springboot.employeedirectory.sensor.Sensor;
import com.james.springboot.employeedirectory.sensor.SensorRepository;
import com.james.springboot.employeedirectory.sensor.SensorService;
import com.james.springboot.employeedirectory.sensor.SensorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
