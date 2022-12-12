package com.udemy.jpahibernate.repository;

import com.udemy.jpahibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RepositoryRestResource(path= "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByNameAndId(String name, Long id);
    List<Course> findByName(String name);
    List<Course> countByName(String name);
    List<Course> deleteByName(String name);
    @Query("Select c From Course c where name like '%services'")
    List<Course> coursesWithServices();

    @Query(value = "Select *From Course c where name like '%services'", nativeQuery = true)
    List<Course> coursesWithServicesUsingNativeQuery();
}
