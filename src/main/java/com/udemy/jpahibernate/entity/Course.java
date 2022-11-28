package com.udemy.jpahibernate.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(
        value= {
                @NamedQuery(name="get_all_courses", query= "Select c From Course c"),
                @NamedQuery(name="get_course_with_services", query="Select c From Course c where name like '% Services'")
        }
)

public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastupdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews= new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Student> students= new ArrayList<>();


    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public Long getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
