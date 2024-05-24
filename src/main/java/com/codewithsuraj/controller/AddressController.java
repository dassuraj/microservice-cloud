package com.codewithsuraj.controller;

import com.codewithsuraj.model.AddressResponse;
import com.codewithsuraj.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping(value = "/address/{employeeId}")
   public ResponseEntity<AddressResponse> findAddressByEmployeeId(@PathVariable("employeeId")int id){
        AddressResponse addressResponse= addressService.getAddressByEmployeeId(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }
}
