package uz.zohidjon.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uz.zohidjon.ecommerce.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
