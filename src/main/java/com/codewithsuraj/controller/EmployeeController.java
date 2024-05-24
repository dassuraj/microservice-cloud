package com.codewithsuraj.controller;

import com.codewithsuraj.model.EmployeeResponse;
import com.codewithsuraj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") int id){
        EmployeeResponse employeeResponse= employeeService.getEmpById(id);
        System.out.println(employeeResponse.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

}
