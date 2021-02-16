package com.james.springboot.vftproductiontracker;

import com.james.springboot.vftproductiontracker.controller.SensorRestController;
import com.james.springboot.vftproductiontracker.sensor.Sensor;
import com.james.springboot.vftproductiontracker.sensor.SensorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SensorRestController.class)

public class SensorRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SensorService service;

    @Test
    public void whenFindAllSensorsThenReturnJsonArray()
            throws Exception {

        Sensor sensor = new Sensor(12345678, 123456, "VLP", 3, "PZN-SS-XX-XXX");

        List<Sensor> allSensors = Arrays.asList(sensor);

        given(service.findAll()).willReturn(allSensors);

        mvc.perform(MockMvcRequestBuilders.get("/rest/sensors/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.sensors").exists());
                //.andExpect(jsonPath("$[0].name", is(alex.getName())));
    }
}
