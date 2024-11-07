package uz.developer.zohidjon.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developer.zohidjon.order.dto.request.OrderLineRequest;
import uz.developer.zohidjon.order.dto.response.OrderLineResponse;
import uz.developer.zohidjon.order.entity.Order;
import uz.developer.zohidjon.order.entity.OrderLine;
import uz.developer.zohidjon.order.repository.OrderLineRepository;
import uz.developer.zohidjon.order.service.OrderLineService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository repository;

    @Override
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(this::toOrderLineResponse)
                .collect(Collectors.toList());
    }

    public void saveOrderLine(OrderLineRequest request) {
        var order = toOrderLine(request);
        repository.save(order);
    }

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
