package uz.developer.zohidjon.payment.dto;

import uz.developer.zohidjon.payment.entity.Customer;
import uz.developer.zohidjon.payment.entity.PaymentMethod;

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