package miu.edu.cse.mysqldtodemo.service.impl;

import miu.edu.cse.mysqldtodemo.dto.AddressDto;
import miu.edu.cse.mysqldtodemo.model.Address;
import miu.edu.cse.mysqldtodemo.repository.AddressRepository;
import miu.edu.cse.mysqldtodemo.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<AddressDto> findAddressById(Integer addressId) {
        Optional<Address> address = addressRepository.findById(addressId);
        AddressDto addressDto = new AddressDto();
        if (address.isPresent()) {
            addressDto.setAddressId(address.get().getAddressId());
            addressDto.setStreet(address.get().getStreet());
            addressDto.setCity(address.get().getCity());
            addressDto.setState(address.get().getState());
        }
        return Optional.of(addressDto);
    }

    @Override
    public void updateAddress(AddressDto address) {
        Address newAddress = new Address();
        newAddress.setStreet(address.getStreet());
        newAddress.setCity(address.getCity());
        newAddress.setState(address.getState());
        addressRepository.save(newAddress);
    }
}
