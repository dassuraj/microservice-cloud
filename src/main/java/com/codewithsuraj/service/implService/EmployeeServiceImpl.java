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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WebClient webClient;
    /**
    //@Autowired
    private RestTemplate restTemplate;
    */

    /**
     * //    @Value("${addressService.base.url}")
     * //    private String addressBaseURL;
     */

    /**
     * this constructor is used to  configure the restTemplate if it is not define inside the config file as bean
    public EmployeeServiceImpl(@Value("${addressService.base.url}") String addressBaseURL,
                               RestTemplateBuilder builder) {
        this.restTemplate = builder.rootUri(addressBaseURL).build();
    }
     **/

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    /**
     * here we are using the rest template(first) as well as web client learning letter
     * @param id
     * @return
     */
    @Override
    public EmployeeResponse getEmpById(int id) {
        Employee employee = employeeRepository.findById(id).get();//db call
        AddressResponse addressResponse = new AddressResponse();
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        /**
         * instead of using restTemplate now we are going to use webclient that will not block the new line or new request
         */
        //addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);//external restapi call
        /**
         * point to be noted
         * RestTemplate is blocking in nature and id doesn't let other line to execute until it get response i.e, it(thread)
         * doesn't accept another request
         */


        /**
         * configuring webclient in config file  for using it i.e, bean configuration for web client
         */
        addressResponse =webClient.get()
                .uri("/address/"+id)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();

        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;

        /**
         * instead of direct https use we can add url in .properties file
         *
         * //    @GetMapping(value = "/employee")
         * //    public String getEmployee() {
         * //        String address = restTemplate.getForObject("http://localhost:8081/address", String.class);
         * //        return "Name:->Suraj::gmail:->suraj@gmail.com::address->" + address;
         * //    }
         */
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
