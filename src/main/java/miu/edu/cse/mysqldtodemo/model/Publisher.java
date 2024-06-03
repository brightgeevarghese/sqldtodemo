package miu.edu.cse.mysqldtodemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.awt.print.Book;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisherId;
    @Column(nullable = false)
    @NotBlank(message = "Empty-Blank-Null not allowed")
    private String publisherName;

    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address primaryAddress;

    public Publisher(String publisherName, Address address) {
        this.publisherName = publisherName;
        this.primaryAddress = address;
    }
}
