package uz.zohidjon.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zohidjon.ecommerce.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}