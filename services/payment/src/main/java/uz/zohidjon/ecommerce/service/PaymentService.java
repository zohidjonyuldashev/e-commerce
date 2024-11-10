package uz.zohidjon.ecommerce.service;

import jakarta.validation.Valid;
import uz.zohidjon.ecommerce.dto.PaymentRequest;

public interface PaymentService {
    Integer createPayment(@Valid PaymentRequest request);
}
