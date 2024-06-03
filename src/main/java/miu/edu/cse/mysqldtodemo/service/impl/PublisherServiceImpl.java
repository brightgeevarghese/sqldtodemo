package miu.edu.cse.mysqldtodemo.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.mysqldtodemo.dto.AddressDto;
import miu.edu.cse.mysqldtodemo.dto.PublisherDto;
import miu.edu.cse.mysqldtodemo.model.Address;
import miu.edu.cse.mysqldtodemo.model.Publisher;
import miu.edu.cse.mysqldtodemo.repository.PublisherRepository;
import miu.edu.cse.mysqldtodemo.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Optional<PublisherDto> addNewPublisher(PublisherDto publisherDto) {
        Publisher newPublisher = new Publisher();
        newPublisher.setPublisherName(publisherDto.getPublisherName());
        newPublisher.setPrimaryAddress(
                new Address(
                        publisherDto.getAddressDto().getStreet(),
                        publisherDto.getAddressDto().getCity(),
                        publisherDto.getAddressDto().getState())
        );
        Publisher publisher = publisherRepository.save(newPublisher);
        PublisherDto responseDto = new PublisherDto();
        responseDto.setPublisherId(publisher.getPublisherId());
        responseDto.setPublisherName(publisher.getPublisherName());
        responseDto.setAddressDto(new AddressDto(publisher.getPrimaryAddress().getAddressId(), publisher.getPrimaryAddress().getStreet(), publisher.getPrimaryAddress().getCity(), publisher.getPrimaryAddress().getState()));
        return Optional.of(responseDto);
    }

    @Override
    public Optional<PublisherDto> findPublisherByName(String name) {
        Optional<Publisher> optionalPublisher = publisherRepository.findByPublisherName(name);
        if (optionalPublisher.isPresent()) {
            return Optional.of(
                    new PublisherDto(
                            optionalPublisher.get().getPublisherId(),
                            optionalPublisher.get().getPublisherName(),
                            new AddressDto(
                                    optionalPublisher.get().getPrimaryAddress().getAddressId(),
                                    optionalPublisher.get().getPrimaryAddress().getStreet(),
                                    optionalPublisher.get().getPrimaryAddress().getCity(),
                                    optionalPublisher.get().getPrimaryAddress().getState()
                            )
                    )
            );
        }
        return Optional.empty();
    }

    @Override
    public void updatePublisher(String publisherName, PublisherDto publisherDto) {
        publisherRepository.findByPublisherName(publisherName)
                .ifPresent(publisher -> {
                    publisher.setPublisherName(publisherDto.getPublisherName());
//                    if (publisherDto.getAddressDto() != null) {
//                        Address address = publisher.getPrimaryAddress();
//                        address.setStreet(publisherDto.getAddressDto().getStreet());
//                        address.setCity(publisherDto.getAddressDto().getCity());
//                        address.setState(publisherDto.getAddressDto().getState());
//                        publisher.setPrimaryAddress(address);
//                    }
                    publisherRepository.save(publisher);
                });
    }

    @Override
    public void deletePublisher(Integer id) {
        publisherRepository.deleteById(id);
    }
}
