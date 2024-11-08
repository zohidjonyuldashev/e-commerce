package uz.developer.zohidjon.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developer.zohidjon.payment.dto.PaymentNotificationRequest;
import uz.developer.zohidjon.payment.dto.PaymentRequest;
import uz.developer.zohidjon.payment.entity.Payment;
import uz.developer.zohidjon.payment.kafka.NotificationProducer;
import uz.developer.zohidjon.payment.repository.PaymentRepository;
import uz.developer.zohidjon.payment.service.PaymentService;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }

    private Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }
        return Payment.builder()
                .id(request.id())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .orderId(request.orderId())
                .build();
    }
}
