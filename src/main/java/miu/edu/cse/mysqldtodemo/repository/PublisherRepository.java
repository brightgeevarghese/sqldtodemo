package miu.edu.cse.mysqldtodemo.repository;

import miu.edu.cse.mysqldtodemo.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByPublisherName(String publisherName);
}
