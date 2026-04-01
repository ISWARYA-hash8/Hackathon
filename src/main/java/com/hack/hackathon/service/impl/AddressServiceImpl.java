package com.hack.hackathon.service.impl;

import com.hack.hackathon.Entity.Address;
import com.hack.hackathon.exception.ResourceNotFoundException;
import com.hack.hackathon.repository.AddressRepository;
import com.hack.hackathon.service.AddressService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address addAddress(Address address) {

        try {
            return addressRepository.save(address);
        } catch (Exception e) {
            throw new RuntimeException("Error while adding address");
        }

    }

    @Override
    public List<Address> getUserAddresses(Long userId) {

        try {
            return addressRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching addresses");
        }

    }

    @Override
    public Address updateAddress(Long id, Address address) {

        try {

            Address existing = addressRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

            existing.setStreet(address.getStreet());
            existing.setCity(address.getCity());
            existing.setState(address.getState());
            existing.setPostalCode(address.getPostalCode());
            existing.setCountry(address.getCountry());

            return addressRepository.save(existing);

        } catch (Exception e) {
            throw new RuntimeException("Error updating address");
        }

    }

    @Override
    public void deleteAddress(Long id) {

        try {

            Address address = addressRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

            addressRepository.delete(address);

        } catch (Exception e) {
            throw new RuntimeException("Error deleting address");
        }

    }
}