package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.entity.Course;
import com.udemy.jpahibernate.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional  //if we need a change in  then transactional should be used
public class CourseRepository {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course); //insert
        } else {
            em.merge(course); //update
        }
        return course;
    }

    public void learnAboutEntityManager(){
        Course course = new Course("Web Services");
        em.persist(course);                     // other changes after persist is also tracked since we are using transactional

        Course course1 = new Course("ORM");
        em.persist(course1);

        em.flush();                             //changes upto this point is sent to the database


        //em.detach(course1);                     //changes to course1 is no longer tracked by the EntityManager
        //em.clear();                             //clears eveything that is being tracked by the EM

        course.setName("Web Services Updated"); //this also gets saved
        course1.setName("ORM Updated");

        em.refresh(course);                     //EM will be refreshed and the data will come directly from the database

    }

    public void addHardcodedReviewsForCourse() {
        Course course= findById(1003L);
        logger.info("course.getReviews ->{}",course.getReviews());

        Review review1= new Review("5","Awesome course");
        Review review2= new Review("4","Stunned by it");

        course.addReview(review1);  //setting relationship between course and review
        review1.setCourse(course);

        course.addReview(review1);
        review2.setCourse(course);

        em.persist(review1);   //saving to the db
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course= findById(courseId);
        logger.info("course.getReviews ->{}",course.getReviews());

        for (Review review: reviews) {
            course.addReview(review);  //setting relationship between course and review
            review.setCourse(course);
            em.persist(review);
        }
    }
}