package uz.developer.zohidjon.customer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import uz.developer.zohidjon.customer.entity.Address;

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