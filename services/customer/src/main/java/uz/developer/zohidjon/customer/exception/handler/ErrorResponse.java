package uz.developer.zohidjon.customer.exception.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
