package com.codewithsuraj.service;

import com.codewithsuraj.model.Address;
import com.codewithsuraj.model.AddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AddressService {
     public List<Address> getAllAddress();
     public AddressResponse getAddressByEmployeeId(int employeeId);
     public Address saveAddress(Address address);
     public Address updateAddress(Address address,int id);
     public void deleteAddress(int id);
}
