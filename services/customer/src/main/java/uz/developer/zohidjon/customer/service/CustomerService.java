package uz.developer.zohidjon.customer.service;

import jakarta.validation.Valid;
import uz.developer.zohidjon.customer.dto.request.CustomerRequest;
import uz.developer.zohidjon.customer.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(@Valid CustomerRequest request);

    void updateCustomer(@Valid CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    Boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteCustomer(String customerId);
}
