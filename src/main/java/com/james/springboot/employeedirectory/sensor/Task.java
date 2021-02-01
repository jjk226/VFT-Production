package com.james.springboot.employeedirectory.sensor;

import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="operation")
    private String operation;

    @Column(name="status")
    private String status;

    @Column(name="comment")
    private String comment;

    public Task(String operation, String status, String comment) {
        this.operation = operation;
        this.status = status;
        this.comment = comment;
    }

    public Task() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
