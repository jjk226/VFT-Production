package com.james.springboot.employeedirectory.security;

import com.james.springboot.employeedirectory.sensor.Sensor;
import com.james.springboot.employeedirectory.sensor.SensorService;
import com.james.springboot.employeedirectory.sensor.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private SensorService sensorService;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, SensorService sensorService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sensorService = sensorService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();
        User admin = new User("admin", encode("admin123"), "ADMIN", "ACCESS_LIST,ADD_EMPLOYEE");
        User james = new User("jjk226", encode("jjk123"), "EMPLOYEE", "ACCESS_LIST");
        User test = new User("test", encode("test123"), "TEST", "ACCESS_LIST,ADD_EMPLOYEE");
        User mscott = new User("mscott", encode("mscott123"), "MANAGER", "ACCESS_LIST");
        List<User> users = Arrays.asList(admin, james, test, mscott);
        for (User u: users) {
            System.out.println("User: " + u.getUsername() + ", permissions: " + u.getPermissions());
        }
        this.userRepository.saveAll(users);
        
    }

    public String encode(String string) {
        return this.passwordEncoder.encode(string);
    }
}
