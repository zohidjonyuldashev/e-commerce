package uz.zohidjon.ecommerce.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zohidjon.ecommerce.dto.request.CustomerRequest;
import uz.zohidjon.ecommerce.dto.response.CustomerResponse;
import uz.zohidjon.ecommerce.entity.Customer;
import uz.zohidjon.ecommerce.exception.CustomerNotFoundException;
import uz.zohidjon.ecommerce.repository.CustomerRepository;
import uz.zohidjon.ecommerce.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public String createCustomer(CustomerRequest request) {
        Customer customer = toCustomer(request);
        repository.save(customer);
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())
                ));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(this::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsById(String customerId) {
        return repository.existsById(customerId);
    }

    @Override
    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(this::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", customerId)));
    }

    @Override
    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    private Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    private CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
