package com.sha.microservicecoursemanagement.service;

import com.sha.microservicecoursemanagement.model.Course;
import com.sha.microservicecoursemanagement.model.Transaction;
import com.sha.microservicecoursemanagement.repository.CourseRepository;
import com.sha.microservicecoursemanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Course> allCourses(){
        return this.courseRepository.findAll();
    }

    @Override
    public List<Transaction> findTransactionsOfUsers(Long userId){
        return this.transactionRepository.findAllByUserId(userId);
    }

    @Override
    public List<Transaction> findTransactionsOfCourse(Long courseId){
        return this.transactionRepository.findAllByCourseId(courseId);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction){
        return this.transactionRepository.save(transaction);
    }

    @Override
    public Course findCourseById(Long id) {
        return this.transactionRepository.findByCourseId(id);
    }
}
