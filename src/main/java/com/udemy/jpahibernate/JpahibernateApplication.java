package com.udemy.jpahibernate;

import com.udemy.jpahibernate.entity.Course;
import com.udemy.jpahibernate.entity.Review;
import com.udemy.jpahibernate.entity.Student;
import com.udemy.jpahibernate.repository.CourseRepository;
import com.udemy.jpahibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpahibernateApplication implements CommandLineRunner {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	private final Logger logger= LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JpahibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		courseRepository.addHardcodedReviewsForCourse();
		studentRepository.saveStudentWithPassport();
//			courseRepository.learnAboutEntityManager();
//
//		List<Review> reviews= new ArrayList<>();
//		reviews.add(new Review("1","Not goof for beginners"));
//		reviews.add(new Review("3","Mediator programmers are potential readers"));
//
//
//		courseRepository.addReviewsForCourse(1001L, reviews);

		//studentRepository.insertHardcodedStudentAndCourse();
		studentRepository.insertStudentAndCourse(new Student("Jill"), new Course("DBA"));
	}
}
