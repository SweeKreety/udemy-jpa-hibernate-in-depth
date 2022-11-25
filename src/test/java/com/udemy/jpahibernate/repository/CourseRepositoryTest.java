package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.JpahibernateApplication;
import com.udemy.jpahibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class CourseRepositoryTest {
	@Autowired
	CourseRepository courseRepository;

	@Test
	public void findById_basicTest() {
	Course course=	courseRepository.findById(1001L);
	assertEquals("Java",course.getName());
	}
	@Test
	@DirtiesContext //executes the test code but resets the data to original since the unit test should not change the original data
	public void deleteById_basicTest() {
		courseRepository.deleteById(1002L);
		assertNull(courseRepository.findById(1002L));
	}
	@Test
	@DirtiesContext //executes the test code but resets the data to original since the unit test should not change the original data
	public void save_basicTest() {
		Course course=	courseRepository.findById(1002L);      //getting a course
		assertEquals("Hibernate", course.getName());

		course.setName("Hibernate Updated");							//updating a course
		courseRepository.save(course);						//saving the course

		Course course1= courseRepository.findById(1002L);		//checking the value
		assertEquals("Hibernate Updated", course1.getName());

	}

	@Test
	@DirtiesContext
	public void learnAboutEntityManager(){
		courseRepository.learnAboutEntityManager();
	}
}
