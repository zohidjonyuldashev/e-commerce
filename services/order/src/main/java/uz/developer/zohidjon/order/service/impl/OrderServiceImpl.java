package uz.developer.zohidjon.order.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.developer.zohidjon.order.client.CustomerClient;
import uz.developer.zohidjon.order.client.PaymentClient;
import uz.developer.zohidjon.order.client.ProductClient;
import uz.developer.zohidjon.order.dto.request.OrderLineRequest;
import uz.developer.zohidjon.order.dto.request.OrderRequest;
import uz.developer.zohidjon.order.dto.request.PaymentRequest;
import uz.developer.zohidjon.order.dto.request.PurchaseRequest;
import uz.developer.zohidjon.order.dto.response.OrderResponse;
import uz.developer.zohidjon.order.entity.Order;
import uz.developer.zohidjon.order.exception.BusinessException;
import uz.developer.zohidjon.order.kafka.OrderConfirmation;
import uz.developer.zohidjon.order.kafka.OrderProducer;
import uz.developer.zohidjon.order.repository.OrderRepository;
import uz.developer.zohidjon.order.service.OrderLineService;
import uz.developer.zohidjon.order.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;

    @Transactional
    @Override
    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = repository.save(toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Integer orderId) {
        return this.repository.findById(orderId)
                .map(this::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }

    private Order toOrder(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();
    }

    private OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
