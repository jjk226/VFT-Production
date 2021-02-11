package com.james.springboot.vftproductiontracker;

import com.james.springboot.vftproductiontracker.sensor.Sensor;
import com.james.springboot.vftproductiontracker.sensor.SensorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SensorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SensorRepository sensorRepository;

    @Test
    public void whenFindBySerialNumberReturnSensor() {
        Sensor sensor = new Sensor(12345678, 123456, "VLP",  3, "PZN-SS-XX-XXX");
        entityManager.persist(sensor);
        entityManager.flush();

        Sensor foundSensor = sensorRepository.findBySerialNumber(sensor.getSerialNumber());

        assertThat(sensor.getSerialNumber()).isEqualTo(foundSensor.getSerialNumber());
    }

    @Test
    public void whenFindBySensorIdReturnSensor() {
        Sensor sensor = new Sensor(12345678, 123456, "VLP",  3, "PZN-SS-XX-XXX");
        entityManager.persist(sensor);
        entityManager.flush();

        Optional<Sensor> tempSensor = sensorRepository.findById(sensor.getId());
        Sensor foundSensor = tempSensor.get();

        assertThat(foundSensor.getId()).isEqualTo(sensor.getId());
    }


}
