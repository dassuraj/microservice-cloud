package com.codewithsuraj.service.serviceImpl;

import com.codewithsuraj.model.Address;
import com.codewithsuraj.model.AddressResponse;
import com.codewithsuraj.repository.AddressRepository;
import com.codewithsuraj.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public AddressResponse getAddressByEmployeeId(int employeeId) {
        Address address = addressRepository.findAddressByEmployeeId(employeeId);
        System.out.println(employeeId);
        AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
        return addressResponse;
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address, int id) {
        boolean isExist=addressRepository.existsById(id);
        if (isExist){
          Address address1=addressRepository.findById(id).get();
          address1.setLane1(address.getLane1());
          address1.setLane2(address.getLane2());
          address1.setState(address.getState());
          address1.setZip(address.getZip());
          return addressRepository.save(address1);
        }
        return null;
    }

    @Override
    public void deleteAddress(int id) {
           addressRepository.deleteById(id);
    }
}
