package com.sha.microservicecoursemanagement.service;

import com.sha.microservicecoursemanagement.model.Course;
import com.sha.microservicecoursemanagement.model.Transaction;

import java.util.List;

public interface CourseService {
    List<Course> allCourses();

    List<Transaction> findTransactionsOfUsers(Long userId);

    List<Transaction> findTransactionsOfCourse(Long courseId);

    Transaction saveTransaction(Transaction transaction);

    Course findCourseById(Long id);
}
