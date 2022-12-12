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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class CourseSpringDataRepositoryTest {

	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseSpringDataRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void findById_CoursePresent(){
		Optional<Course> courseOptional= repository.findById(1001L);
		assertTrue(courseOptional.isPresent());
	}
	@Test
	public void findById_CourseNotPresent(){
		Optional<Course> courseOptional= repository.findById(2001L);
		assertFalse(courseOptional.isPresent());
	}
	@Test
	public void playingAroundWithSpringDataRepository(){
//		Course course= new Course("Microservices");
//		repository.save(course);
//		course.setName("Microservices updated");
//		repository.save(course);

		logger.info("Courses- > {}", repository.findAll());
		logger.info("Count- > {}",	repository.count());

	}
	@Test
	public void sort(){
		List<Course> course = repository.findAll(Sort.by(Sort.Direction.DESC, "name"));
		logger.info("Sorted Courses- > {}", course);
		logger.info("Count- > {}",	repository.count());
	}
	@Test
	public void pagination(){
		PageRequest pageRequest= PageRequest.of(0, 3);

		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page- > {}", firstPage.getContent());

		Pageable secondPageable= firstPage.nextPageable();
		Page<Course> secondPage= repository.findAll(secondPageable);
		logger.info("Second Page- > {}", secondPage.getContent());

		Pageable thirdPageable= secondPage.nextPageable();
		Page<Course> thirdPage= repository.findAll(thirdPageable);
		logger.info("Third Page- > {}", thirdPage.getContent());
	}
	@Test
	public void findUsingName(){
		logger.info("Find By Name- > {}",	repository.findByName("English"));
		logger.info("Find The Count- > {}", repository.countByName("Web"));
	}
}
