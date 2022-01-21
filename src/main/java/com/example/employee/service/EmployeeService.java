package com.example.employee.service;

import com.example.employee.request.EmployeeRequest;
import com.example.employee.to.EmployeeDetailsTO;

import java.util.List;

public interface EmployeeService {

    public String createEmployee(EmployeeRequest employeeRequest);
    List<EmployeeDetailsTO> getEmployee();
    List<EmployeeDetailsTO> getByIdEmployee(int id);
    public String updateEmployee(EmployeeRequest employeeRequest);
    public String deleteEmployee(int id);
}
