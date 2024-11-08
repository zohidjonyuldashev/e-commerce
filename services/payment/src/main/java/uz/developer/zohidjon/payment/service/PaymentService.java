package uz.developer.zohidjon.payment.service;

import jakarta.validation.Valid;
import uz.developer.zohidjon.payment.dto.PaymentRequest;

public interface PaymentService {
    Integer createPayment(@Valid PaymentRequest request);
}
