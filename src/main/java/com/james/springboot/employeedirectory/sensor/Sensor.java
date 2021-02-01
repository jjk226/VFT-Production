package com.james.springboot.employeedirectory.sensor;

import com.james.springboot.employeedirectory.validation.SensorFlow;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="production_order")
    private int productionOrder;

    @Column(name="serial_number")
    @Min(1)
    @Max(199999)
    private int serialNumber;

    @Column(name="sensor_type")
    private String sensorType;

    @Column(name="sensor_flow")
    @SensorFlow({3,15,50})
    private int sensorFlow;

    @Column(name="part_number")
    private String partNumber;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="sensor_id")
    private List<Task> tasks;

    public Sensor() {}

    public Sensor(int productionOrder, int serialNumber, String sensorType, int sensorFlow, String partNumber) {
        this.productionOrder = productionOrder;
        this.serialNumber = serialNumber;
        this.sensorType = sensorType;
        this.sensorFlow = sensorFlow;
        this.partNumber = partNumber;
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(int productionOrder) {
        this.productionOrder = productionOrder;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public int getSensorFlow() {
        return sensorFlow;
    }

    public void setSensorFlow(int sensorFlow) {
        this.sensorFlow = sensorFlow;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if(tasks == null) {
            this.tasks = new ArrayList<>();
        }
        this.tasks.add(task);
    }
}
