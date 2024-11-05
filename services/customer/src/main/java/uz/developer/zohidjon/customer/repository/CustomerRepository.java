package uz.developer.zohidjon.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uz.developer.zohidjon.customer.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
