package uz.developer.zohidjon.customer.dto.response;

import uz.developer.zohidjon.customer.entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
