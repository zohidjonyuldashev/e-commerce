package uz.developer.zohidjon.order.kafka;

import uz.developer.zohidjon.order.dto.response.CustomerResponse;
import uz.developer.zohidjon.order.dto.response.PurchaseResponse;
import uz.developer.zohidjon.order.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
