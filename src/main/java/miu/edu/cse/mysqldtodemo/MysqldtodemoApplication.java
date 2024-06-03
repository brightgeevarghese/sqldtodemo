package miu.edu.cse.mysqldtodemo;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.mysqldtodemo.dto.AddressDto;
import miu.edu.cse.mysqldtodemo.dto.PublisherDto;
import miu.edu.cse.mysqldtodemo.model.Address;
import miu.edu.cse.mysqldtodemo.service.AddressService;
import miu.edu.cse.mysqldtodemo.service.PublisherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@RequiredArgsConstructor
@SpringBootApplication
public class MysqldtodemoApplication {

    private final PublisherService publisherService;
    private final AddressService addressService;

    public static void main(String[] args) {
        SpringApplication.run(MysqldtodemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            AddressDto addressDto = new AddressDto("2000 N St", "Fairfield", "IA");
            PublisherDto publisherDto = new PublisherDto("Apres");
//            PublisherDto publisherDto = new PublisherDto(" ");//ConstraintViolationException
            publisherDto.setAddressDto(addressDto);
            addPublisher(publisherDto).ifPresent(
                    publisher -> {
                        System.out.println(publisher.getPublisherName()+": "+publisher.getAddressDto()+" is saved");
                    }
            );
            System.out.println(publisherService.findPublisherByName("Apres").get());
            //Update Publisher name and address
            PublisherDto publisherDto1 = new PublisherDto("Apress Ltd.");
//            AddressDto address = new AddressDto();
//            address.setStreet("3000 N St");
//            address.setCity("Washington");
//            address.setState("Iowa");
//            publisherDto1.setAddressDto(address);
            publisherService.updatePublisher("Apres", publisherDto1);
            System.out.println("after update");
            System.out.println(publisherService.findPublisherByName("Apress Ltd.").get());
            publisherService.deletePublisher(1);
            addressService.findAddressById(1)
                    .ifPresent(System.out::println);
        };
    }

    private Optional<PublisherDto> addPublisher(PublisherDto publisherDto) {
        return publisherService.addNewPublisher(publisherDto);
    }
}
