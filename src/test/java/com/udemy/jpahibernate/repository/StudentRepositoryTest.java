package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.JpahibernateApplication;
import com.udemy.jpahibernate.entity.Passport;
import com.udemy.jpahibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class StudentRepositoryTest {

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	//Persistence Context tracks all the changes
	public void retrieveStudentAndPassportDetails() {
	Student student= em.find(Student.class, 2001L);
	logger.info("Student -> {}", student);
	logger.info("Passport -> {}", student.getPassport());
	}

/*
	@Test
	@Transactional  //if something fails it will be rollbacked
	public void simple_test(){
		//retrieve student
		Student student= em.find(Student.class, 2002L);
		//Persistence Context(student)

		//retrieve passport
		Passport passport= student.getPassport();
		//Persistence Context(student,passport)

		//update passport
		passport.setNumber("Z12345");
		//Persistence Context(student,passport++)

		//update student
		student.setName("Alexa");
		//Persistence Context(student++,passport++)
	}
 */
	@Test
	@Transactional
	//Persistence Context tracks all the changes
	public void retrievePassportAndAssociatedStudent() {
	Passport passport= em.find(Passport.class, 4001L);
	logger.info("passport -> {}", passport);
	logger.info("Student -> {}", passport.getStudent());
}
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student= em.find(Student.class, 2001L);
		logger.info("Student -> {}", student);
		logger.info("Courses -> {}", student.getCourses());
	}
}
