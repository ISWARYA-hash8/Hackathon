package com.hack.hackathon.service;

import com.hack.hackathon.Entity.Address;
import java.util.List;

public interface AddressService {

    Address addAddress(Address address);

    List<Address> getUserAddresses(Long userId);

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);

}