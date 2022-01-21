package com.example.employee.dao;

import com.example.employee.model.EmployeeDetailsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends MongoRepository<EmployeeDetailsModel,Integer> {
}
