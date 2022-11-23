package com.udemy.jpahibernate;

import com.udemy.jpahibernate.entity.Course;
import com.udemy.jpahibernate.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpahibernateApplication implements CommandLineRunner {
	@Autowired
	private CourseRepository repository;

	private final Logger logger= LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JpahibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
 		Course course= repository.findById(1001L);
		logger.info("Course with id 1001 -> {}", course);
		repository.deleteById(1001L);
		repository.save(new Course("Microservices"));
		repository.learnAboutEntityManager();
	}
}
