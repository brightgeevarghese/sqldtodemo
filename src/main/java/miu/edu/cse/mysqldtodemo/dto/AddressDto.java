package miu.edu.cse.mysqldtodemo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Integer addressId;
    private String street;
    private String city;
    private String state;

    public AddressDto(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }
}
