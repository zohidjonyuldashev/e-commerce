package uz.developer.zohidjon.order.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import uz.developer.zohidjon.order.entity.PaymentMethod;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
