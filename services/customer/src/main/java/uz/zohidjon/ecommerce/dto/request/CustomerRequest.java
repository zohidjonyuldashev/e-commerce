package uz.zohidjon.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import uz.zohidjon.ecommerce.entity.Address;

public record CustomerRequest(
        String id,
        @NotNull(message = "firstname is required")
        String firstname,
        @NotNull(message = "lastname is required")
        String lastname,
        @NotNull(message = "email is required")
        @Email(message = "email is not valid")
        String email,
        @NotNull(message = "firstname is required")
        Address address
) {
}