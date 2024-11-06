package uz.developer.zohidjon.product.service;

import jakarta.validation.Valid;
import uz.developer.zohidjon.product.dto.request.ProductPurchaseRequest;
import uz.developer.zohidjon.product.dto.request.ProductRequest;
import uz.developer.zohidjon.product.dto.response.ProductPurchaseResponse;
import uz.developer.zohidjon.product.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer createProduct(@Valid ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
