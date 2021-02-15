package com.james.springboot.vftproductiontracker;

import com.james.springboot.vftproductiontracker.dao.EmployeeRepository;
import com.james.springboot.vftproductiontracker.sensor.Sensor;
import com.james.springboot.vftproductiontracker.sensor.SensorRepository;
import com.james.springboot.vftproductiontracker.sensor.SensorService;
import com.james.springboot.vftproductiontracker.sensor.SensorServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class SensorServiceImplTest {

    @TestConfiguration
    static class SensorServiceImplTestConfiguration {

        @Bean
        public SensorService sensorService(SensorRepository sensorRepository) {
            return new SensorServiceImpl(sensorRepository);
            //Takes in the sensorRepository MockBean as parameter.
        }
    }

    @Autowired
    private SensorService sensorService;

    @MockBean
    private SensorRepository sensorRepository;

    @Before
    public void setUp() {
        Sensor sensor = new Sensor(12345678, 123456, "VLP", 3, "PZN-SS-XX-XXX");

        Mockito.when(sensorRepository.findBySerialNumber(sensor.getSerialNumber())).thenReturn(sensor);
    }

    @Test
    public void whenFindBySerialNumberReturnValidSensor() {
        int serialNumber = 123456;
        Sensor sensor = sensorService.findBySerialNumber(serialNumber);

        assertThat(sensor.getSerialNumber()).isEqualTo(serialNumber);
    }


}
