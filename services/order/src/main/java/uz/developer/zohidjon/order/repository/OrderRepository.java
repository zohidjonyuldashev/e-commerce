package uz.developer.zohidjon.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developer.zohidjon.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}