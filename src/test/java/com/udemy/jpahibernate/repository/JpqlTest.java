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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class JpqlTest {
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;

	@Test
	public void jpql_basicTest() {
		List resultList = em.createNamedQuery("get_all_courses").getResultList();
		logger.info("Select c From Course c-> {}", resultList);
	}
	@Test
	public void jpql_typedTest() {
		TypedQuery<Course> query =
				em.createNamedQuery("get_all_courses", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select c From Course c typedQuery-> {}", resultList);
	}
	@Test
	public void jpql_whereTest() {
		TypedQuery<Course> query =
				em.createNamedQuery("get_course_with_services", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select c From Course c where name like'% Services'-> {}", resultList);
	}



}
