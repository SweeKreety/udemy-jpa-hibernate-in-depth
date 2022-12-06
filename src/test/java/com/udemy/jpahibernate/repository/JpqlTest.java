package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.JpahibernateApplication;
import com.udemy.jpahibernate.entity.Course;
import com.udemy.jpahibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class JpqlTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
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

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query =
				em.createQuery("Select c from Course c where c.students is empty", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Results-> {}", resultList);
	}

	@Test
	public void jpql_courses_with_at_least_two_students() {
		TypedQuery<Course> query =
				em.createQuery("Select c from Course c where size(c.students) >=2", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Results-> {}", resultList);
	}

	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query =
				em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Results-> {}", resultList);
	}

	@Test
	public void jpql_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query =
				em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);

		List<Student> resultList = query.getResultList();

		logger.info("Results-> {}", resultList);
	}

	@Test
	public void join() {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s"); //selects those courses which have students
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size-> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}

		@Test
		public void left_join () {
			Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s"); //selects those courses which does not have student as well
			List<Object[]> resultList = query.getResultList();
			logger.info("Results Size-> {}", resultList.size());
			for (Object[] result : resultList) {
				logger.info("Course {} Student {}", result[0], result[1]);
			}
		}

	@Test
	public void cross_join () {
		Query query = em.createQuery("Select c, s from Course c, Student s");  //maps every course to every student- basically a cross product
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size-> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}

}

