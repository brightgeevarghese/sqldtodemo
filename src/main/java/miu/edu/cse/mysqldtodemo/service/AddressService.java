package miu.edu.cse.mysqldtodemo.service;

import miu.edu.cse.mysqldtodemo.dto.AddressDto;

import java.util.Optional;

public interface AddressService {
    Optional<AddressDto> findAddressById(Integer addressId);
    void updateAddress(AddressDto address);
}
