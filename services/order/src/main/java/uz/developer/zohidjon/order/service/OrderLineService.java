package uz.developer.zohidjon.order.service;

import uz.developer.zohidjon.order.dto.request.OrderLineRequest;
import uz.developer.zohidjon.order.dto.response.OrderLineResponse;

import java.util.List;

public interface OrderLineService {

    List<OrderLineResponse> findAllByOrderId(Integer orderId);

    void saveOrderLine(OrderLineRequest request);
}
