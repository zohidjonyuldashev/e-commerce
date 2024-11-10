package uz.zohidjon.ecommerce.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
