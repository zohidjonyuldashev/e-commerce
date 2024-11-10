package uz.zohidjon.ecommerce.dto.response;

import uz.zohidjon.ecommerce.entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
