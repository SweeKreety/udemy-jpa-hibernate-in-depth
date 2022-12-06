package com.udemy.jpahibernate.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee{

    protected PartTimeEmployee(){} //no args constructor for JPA

    public PartTimeEmployee(String name, BigDecimal hourlyWage){
        super(name);
        this.hourlyWage= hourlyWage;
    }
    private BigDecimal hourlyWage;
}
