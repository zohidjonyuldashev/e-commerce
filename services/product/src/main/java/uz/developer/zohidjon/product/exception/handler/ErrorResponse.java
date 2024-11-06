package uz.developer.zohidjon.product.exception.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
