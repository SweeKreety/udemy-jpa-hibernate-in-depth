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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class CriteriaQueryTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;

	@Test
	public void criteria_query_basicTest() {
//Steps for writing criteria query

//		1. Use criteria builder
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

//		2.Define roots for tables involved in the query
		Root<Course> courseRoot = cq.from(Course.class);

//		3.Define predicates etc using criteria builder
//		4.Add predicates to criteria query


//		 5. Build typed query using entity manager and criteria query
		TypedQuery query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList= query.getResultList();
		logger.info("Typed Query-> {}", resultList);
	}
	@Test
	public void all_courses_having_pa(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		Predicate like100Steps = cb.like(courseRoot.get("name"), "%PA"); //pass column name and condition to check for

		cq.where(like100Steps);

		TypedQuery query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList= query.getResultList();
		logger.info("Typed Query-> {}", resultList);
	}
	@Test
	public void all_courses_without_students(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		cq.where(studentsIsEmpty);

		TypedQuery query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList= query.getResultList();
		logger.info("Typed Query-> {}", resultList);
	}
	@Test
	public void join(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		Join<Object, Object> join = courseRoot.join("students");

		TypedQuery query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList= query.getResultList();
		logger.info("Typed Query-> {}", resultList);
	}
	@Test
	public void left_join(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		Root<Course> courseRoot = cq.from(Course.class);

		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		TypedQuery query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList= query.getResultList();
		logger.info("Typed Query-> {}", resultList);
	}
}

