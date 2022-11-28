package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.entity.Course;
import com.udemy.jpahibernate.entity.Passport;
import com.udemy.jpahibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional  //if we need a change in  then transactional should be used
public class StudentRepository {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student); //insert
        } else {
            em.merge(student); //update
        }
        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("A12345");
        em.persist(passport);

        Student student = new Student("Nick");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertHardcodedStudentAndCourse(){
        Student student= new Student("Jack");
        Course course= new Course("DBA");
        em.persist(student);
        em.persist(course);
        student.addCourses(course);
        course.addStudents(student);
        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course){
        student.addCourses(course);
        course.addStudents(student);

        em.persist(student);
        em.persist(course);
    }
}