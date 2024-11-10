package uz.zohidjon.ecommerce.dto;

import uz.zohidjon.ecommerce.entity.Customer;
import uz.zohidjon.ecommerce.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}