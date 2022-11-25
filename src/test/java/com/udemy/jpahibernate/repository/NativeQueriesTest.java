package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.JpahibernateApplication;
import com.udemy.jpahibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpahibernateApplication.class)
public class NativeQueriesTest {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;

	@Test
	public void native_queries_basic() {
		Query query= em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List resultList = query.getResultList();

		logger.info("Select * From Course -> {}", resultList);
	}

	@Test
	public void native_queries_with_parameter() {
		Query query= em.createNativeQuery("SELECT * FROM COURSE where id=?", Course.class);
		query.setParameter(1, 1003L);
		List resultList = query.getResultList();

		logger.info("Select * From Course where id=? -> {}", resultList);
	}
	@Test
	public void native_queries_with_named_parameter() {
		Query query= em.createNativeQuery("SELECT * FROM COURSE where id= :id", Course.class);
		query.setParameter("id", 1003L);
		List resultList = query.getResultList();

		logger.info("Select * From Course where id=? -> {}", resultList);
	}
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query= em.createNativeQuery("Update COURSE set name= 'Cloud Computing' where id=1", Course.class);
		int noOfRowsUpdated = query.executeUpdate();

		logger.info("Number of rows updated -> {}", noOfRowsUpdated);
	}
}

