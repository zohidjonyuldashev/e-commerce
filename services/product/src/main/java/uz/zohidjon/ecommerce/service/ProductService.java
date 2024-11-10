package uz.zohidjon.ecommerce.service;

import jakarta.validation.Valid;
import uz.zohidjon.ecommerce.dto.request.ProductPurchaseRequest;
import uz.zohidjon.ecommerce.dto.request.ProductRequest;
import uz.zohidjon.ecommerce.dto.response.ProductPurchaseResponse;
import uz.zohidjon.ecommerce.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer createProduct(@Valid ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
