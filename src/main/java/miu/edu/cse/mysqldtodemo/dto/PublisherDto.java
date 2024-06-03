package miu.edu.cse.mysqldtodemo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {
    private Integer publisherId;
    @NotBlank(message = "It cannot be blank.....")
    private String publisherName;
    private AddressDto addressDto;

    public PublisherDto(String publisherName) {
        this.publisherName = publisherName;
    }
}
