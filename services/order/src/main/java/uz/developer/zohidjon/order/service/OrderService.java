package uz.developer.zohidjon.order.service;

import jakarta.validation.Valid;
import uz.developer.zohidjon.order.dto.request.OrderRequest;
import uz.developer.zohidjon.order.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer createOrder(@Valid OrderRequest request);

    List<OrderResponse> findAllOrders();

    OrderResponse findById(Integer orderId);
}
