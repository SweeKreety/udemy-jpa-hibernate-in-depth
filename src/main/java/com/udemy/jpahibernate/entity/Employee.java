package com.udemy.jpahibernate.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass //eliminates inheritance relationship between superclass and subclasses
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)


//Abstract class cannot be used to create objects. To access it, it should be inherited from another class
public abstract class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
