package uz.developer.zohidjon.order.dto.response;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}