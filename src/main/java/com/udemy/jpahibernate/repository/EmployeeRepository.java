package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional  //if we need a change in  then transactional should be used
public class EmployeeRepository {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManager em;

    //insert and retrieve all employees
    public void insert(Employee employee){
        em.persist(employee);
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
        return em.createQuery("Select e from PartTimeEmployee e", PartTimeEmployee.class)
                .getResultList();
    }
    public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
        return em.createQuery("Select e from FullTimeEmployee e", FullTimeEmployee.class)
                .getResultList();
    }
    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }


}