package uz.zohidjon.ecommerce.service;

import jakarta.validation.Valid;
import uz.zohidjon.ecommerce.dto.request.CustomerRequest;
import uz.zohidjon.ecommerce.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(@Valid CustomerRequest request);

    void updateCustomer(@Valid CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    Boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteCustomer(String customerId);
}
