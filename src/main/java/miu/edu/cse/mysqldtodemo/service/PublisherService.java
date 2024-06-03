package miu.edu.cse.mysqldtodemo.service;

import miu.edu.cse.mysqldtodemo.dto.PublisherDto;

import java.util.Optional;

public interface PublisherService {
    Optional<PublisherDto> addNewPublisher(PublisherDto publisher);
    Optional<PublisherDto> findPublisherByName(String name);
    void updatePublisher(String publisherName, PublisherDto publisher);
    void deletePublisher(Integer id);
}
