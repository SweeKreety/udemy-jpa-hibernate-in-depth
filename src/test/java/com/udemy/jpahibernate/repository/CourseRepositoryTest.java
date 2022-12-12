package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.JpahibernateApplication;
import com.udemy.jpahibernate.entity.Course;
import com.udemy.jpahibernate.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class CourseRepositoryTest {

	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	@Test
	public void findById_basicTest() {
	Course course=	courseRepository.findById(1001L);
	assertEquals("Java",course.getName());
	}

	@Test

	public void findById_firstLevelCacheDemo() {
		Course course=	courseRepository.findById(1001L);
		logger.info("First Course Retrieved {}", course);

		Course course1=	courseRepository.findById(1001L);
		logger.info("First Course Retrieved Twice{}", course1);

		assertEquals("Java",course.getName());
		assertEquals("Java",course1.getName());
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

	@Test
	@DirtiesContext
	@Transactional
	public void retrieveReviewsForCourse(){

		Course course= courseRepository.findById(1001L);
		logger.info("Review for course with id 1001 {}", course.getReviews());
	}

	@Test
	@DirtiesContext
	@Transactional
	public void retrieveCourseForReview(){

		Review review= em.find(Review.class, 5001L);
		logger.info("Course for review with id 5001 {}", review.getCourse());
	}
}
