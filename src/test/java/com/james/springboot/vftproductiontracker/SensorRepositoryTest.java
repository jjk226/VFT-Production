package com.james.springboot.vftproductiontracker;

import com.james.springboot.vftproductiontracker.sensor.Sensor;
import com.james.springboot.vftproductiontracker.sensor.SensorRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SensorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SensorRepository sensorRepository;

    public Sensor createSensor() {
        return new Sensor(12345678, 123456, "VLP", 3, "PZN-SS-XX-XXX");
    }

    @Test
    public void whenFindBySerialNumberReturnSensor() {
        Sensor sensor = this.createSensor();
        entityManager.persist(sensor);
        entityManager.flush();

        Sensor foundSensor = sensorRepository.findBySerialNumber(sensor.getSerialNumber());
        assertThat(sensor.getSerialNumber()).isEqualTo(foundSensor.getSerialNumber());
    }

    @Test
    public void whenFindBySensorIdReturnSensor() {
        Sensor sensor = this.createSensor();
        entityManager.persist(sensor);
        entityManager.flush();

        Optional<Sensor> tempSensor = sensorRepository.findById(sensor.getId());
        Sensor foundSensor = tempSensor.get();
        assertThat(foundSensor.getId()).isEqualTo(sensor.getId());
    }

    @Test
    public void checkIfSensorBySerialNumberExistsAndReturnTrue() {
        Sensor sensor = this.createSensor();
        entityManager.persist(sensor);
        entityManager.flush();

        assertThat(sensorRepository.existsBySerialNumber(sensor.getSerialNumber())).isEqualTo(true);
    }

    @Test
    public void findBySensorTypeAndReturnSensors() {
        Sensor sensorAlpha = this.createSensor();
        sensorAlpha.setSensorType("VLP");

        Sensor sensorBeta = this.createSensor();
        sensorBeta.setSensorType("VLP");

        Sensor sensorOmega = this.createSensor();
        sensorOmega.setSensorType("STD");

        List<Sensor> sensors = Lists.newArrayList(sensorAlpha, sensorBeta, sensorOmega);

        sensors.stream().forEach(s -> entityManager.persist(s));
        entityManager.flush();

        assertThat(this.sensorRepository.findBySensorType("VLP").size()).isEqualTo(2);
    }


}
