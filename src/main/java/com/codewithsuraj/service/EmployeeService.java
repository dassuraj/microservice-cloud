package com.codewithsuraj.service;

import com.codewithsuraj.model.Employee;
import com.codewithsuraj.model.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployee();
    EmployeeResponse getEmpById(int id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee,int id);
    void deleteEmployee(int id);
}
