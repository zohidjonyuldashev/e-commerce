package uz.developer.zohidjon.order.dto.request;

import uz.developer.zohidjon.order.dto.response.CustomerResponse;
import uz.developer.zohidjon.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
