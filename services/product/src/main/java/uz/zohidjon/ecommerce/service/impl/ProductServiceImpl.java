package uz.zohidjon.ecommerce.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.zohidjon.ecommerce.dto.request.ProductPurchaseRequest;
import uz.zohidjon.ecommerce.dto.request.ProductRequest;
import uz.zohidjon.ecommerce.dto.response.ProductPurchaseResponse;
import uz.zohidjon.ecommerce.dto.response.ProductResponse;
import uz.zohidjon.ecommerce.entity.Category;
import uz.zohidjon.ecommerce.entity.Product;
import uz.zohidjon.ecommerce.exception.ProductPurchaseException;
import uz.zohidjon.ecommerce.repository.ProductRepository;
import uz.zohidjon.ecommerce.service.ProductService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Integer createProduct(ProductRequest request) {
        Product product = toProduct(request);
        return repository.save(product).getId();
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(this::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + productId));
    }

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }


    private Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    private ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    private ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
