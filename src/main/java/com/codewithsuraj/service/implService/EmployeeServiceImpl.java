package com.codewithsuraj.service.implService;

import com.codewithsuraj.model.AddressResponse;
import com.codewithsuraj.model.Employee;
import com.codewithsuraj.model.EmployeeResponse;
import com.codewithsuraj.repository.EmployeeRepository;
import com.codewithsuraj.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
//    @Autowired
    private RestTemplate restTemplate;
//
//    @Value("${addressService.base.url}")
//    private String addressBaseURL;

    public EmployeeServiceImpl(@Value("${addressService.base.url}")String addressBaseURL,
                           RestTemplateBuilder builder){
        this.restTemplate=builder.rootUri(addressBaseURL).build();
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeResponse getEmpById(int id) {
        Employee employee = employeeRepository.findById(id).get();
        AddressResponse addressResponse = new AddressResponse();
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        boolean isExist = employeeRepository.existsById(id);
        if (isExist) {
            Employee employee1 = employeeRepository.findById(id).get();
            employee1.setName(employee.getName());
            employee1.setEmail(employee.getEmail());
            employee1.setSalary(employee.getSalary());
            employee1.setBloodGroup(employee.getBloodGroup());
            return employeeRepository.save(employee1);
        }
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
